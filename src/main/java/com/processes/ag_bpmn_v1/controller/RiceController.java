package com.processes.ag_bpmn_v1.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.processes.ag_bpmn_v1.entity.TaskEntity;
import com.processes.ag_bpmn_v1.service.RiceService;
import com.processes.ag_bpmn_v1.util.Result;
import org.apache.commons.io.FileUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.flowable.engine.*;
import org.flowable.engine.common.api.repository.EngineDeployment;
import org.flowable.engine.common.impl.EngineDeployer;
import org.flowable.engine.impl.bpmn.deployer.BpmnDeployer;
import org.flowable.engine.impl.bpmn.deployer.BpmnDeploymentHelper;
import org.flowable.engine.impl.persistence.entity.DeploymentEntityImpl;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.processes.ag_bpmn_v1.util.constant.*;

@RestController
@RequestMapping(value = "rice")
public class RiceController {

    @Autowired
    private RiceService riceService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;


    public String getXml() throws IOException {
        File file = ResourceUtils.getFile("classpath:rice.bpmn20.xml");
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        return content;
    }
    /**
     * ?????????????????????????????????xml??????????????????
     * @param json
     * @return
     */
    @RequestMapping(value = "addProcess", method = RequestMethod.POST)
    public Result addFile(@RequestBody JSONObject json) {

        //??????????????????
//        json.put("fileName","rice0801");
//        json.put("content",getXml());

        String fileName= (String) json.get("fileName");
        String content= (String) json.get("content");

        fileName += SUFFIX;
        //?????? ProcessEngine ??????
        ProcessEngine engine= ProcessEngines.getDefaultProcessEngine();
        //?????? RepositoryService
        RepositoryService service =engine.getRepositoryService();
        //??????????????? deploy ????????????????????????????????????
        Deployment deploy=service.createDeployment().name(fileName).addString(fileName,content).deploy();
        System.out.println(deploy.getId() + "    " + deploy.getName());
        return Result.ok(SUCCESS_CODE, SUCCESS_MSG, deploy.getName());
    }

    /**
     * ????????????xml??????dmn??????
     * @Description
     * @Author jiangyongtao
     * @Date 2022/5/27 16:47
     */

    @RequestMapping(value = "addDmnXml", method = RequestMethod.POST)
    public Result addDmnXml(@RequestBody JSONObject jsonInput) {
        String DMNTableName = (String) jsonInput.get("DMNTableName");
        System.out.println(DMNTableName);
        String DMNTableId = (String) jsonInput.get("DMNTableId");
        String DMNTableDescription = (String) jsonInput.get("DMNTableDescription");
        String hitPolicy = (String) jsonInput.get("hitPolicy");
        JSONArray DMNInput = jsonInput.getJSONArray("DMNInput");
        JSONArray DMNOutput = jsonInput.getJSONArray("DMNOutput");
        JSONArray DMNRule = jsonInput.getJSONArray("DMNRule");
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document document = db.newDocument();

            document.setXmlStandalone(true);

            Element definitions = document.createElement("definitions");
            definitions.setAttribute("xmlns", "http://www.omg.org/spec/DMN/20151101");
            //definitions.setAttribute("id",DMNId);   //to do
            definitions.setAttribute("name", DMNTableName);
            definitions.setAttribute("namespace", "http://www.flowable.org/dmn");

            Element decision = document.createElement("decision");
            decision.setAttribute("id", DMNTableId);
            decision.setAttribute("name", DMNTableName);

            Element description = document.createElement("description");
            description.setTextContent(DMNTableDescription);

            decision.appendChild(description);

            Element decisionTable = document.createElement("decisionTable");
            decisionTable.setAttribute("hitPolicy", hitPolicy);

            Iterator it = DMNInput.iterator();
            int inOutNum = 1;
            while (it.hasNext()) {
                String inputExpressionId = "inputExpression_" + inOutNum;
                JSONObject temp = (JSONObject) it.next();

                Element input = document.createElement("input");
                input.setAttribute("label", (String) temp.get("name"));

                Element inputExpression = document.createElement("inputExpression");
                inputExpression.setAttribute("id", inputExpressionId);
                inOutNum++;
                inputExpression.setAttribute("typeRef", (String) temp.get("typeRef"));

                Element text = document.createElement("text");
                text.setTextContent((String) temp.get("id"));

                inputExpression.appendChild(text);
                input.appendChild(inputExpression);
                decisionTable.appendChild(input);

            }

            it = DMNOutput.iterator();
            while (it.hasNext()) {
                String outputExpressionId = "outputExpression_" + inOutNum;
                inOutNum++;
                JSONObject temp = (JSONObject) it.next();
                Element output = document.createElement("output");
                output.setAttribute("id", outputExpressionId);
                output.setAttribute("label", (String) temp.get("name"));
                output.setAttribute("name", (String) temp.get("id"));
                output.setAttribute("typeRef", (String) temp.get("typeRef"));

                decisionTable.appendChild(output);
            }

            it = DMNRule.iterator();
            int ruleNum = 1;
            while (it.hasNext()) {
                JSONArray ruleArray = (JSONArray) it.next();
                int temp = 1;
                Iterator ruleIt = ruleArray.iterator();
                Element rule = document.createElement("rule");
                while (ruleIt.hasNext()) {
                    JSONObject ruleTemp = (JSONObject) ruleIt.next();
                    String ruleId;
                    Element entry;
                    if (ruleTemp.get("type").toString().equals("input")) {
                        ruleId = "inputEntry_" + temp + "_" + ruleNum;
                        entry = document.createElement("inputEntry");
                        entry.setAttribute("id", ruleId);
                    } else if (ruleTemp.get("type").toString().equals("output")) {
                        ruleId = "outputEntry_" + temp + "_" + ruleNum;
                        entry = document.createElement("outputEntry");
                        entry.setAttribute("id", ruleId);
                    } else {
                        throw new Exception();
                    }
                    Element ruleText = document.createElement("text");
                    CDATASection cdata = document.createCDATASection(ruleTemp.get("ruleText").toString());
                    ruleText.appendChild(cdata);
                    entry.appendChild(ruleText);
                    rule.appendChild(entry);
                    decisionTable.appendChild(rule);
                    temp++;
                }
                ruleNum++;

            }

            decision.appendChild(decisionTable);

            definitions.appendChild(decision);

            document.appendChild(definitions);

            // ??????TransformerFactory??????
            TransformerFactory tff = TransformerFactory.newInstance();
            // ?????? Transformer??????
            Transformer tf = tff.newTransformer();

            // ??????????????????????????????
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            // ??????xml?????????????????????
            tf.transform(new DOMSource(document), new StreamResult(new File(DMNTableId + ".xml")));
            System.out.println("??????xml??????");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("??????xml??????");
            return Result.err(FAILED_CODE_OUT_ERR, FAILED_MSG, null);
        }
        return Result.ok(SUCCESS_CODE, SUCCESS_MSG, null);
    }

    /**
    * @Description  ??????????????????,??????????????????
    * @Author  jiangyongtao
    * @Date   2022/6/2 10:30
    */
    @RequestMapping(value = "startOneProcess", method = RequestMethod.POST)
    public Result startProcess(@RequestParam String pName) {
        //??????????????????
        HashMap<String, Object> map = new HashMap<>();
        //??????????????????????????????????????????????????????id
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(pName, map);
        //???????????????????????????
        String pLog = "????????????????????????Id: " + processInstance.getId() + ", ????????????: " + pName;
        System.out.println(pLog);
        int pId = Integer.parseInt(processInstance.getId());
        TaskEntity taskEntity = riceService.selectTaskId(pId);
        String taskId = taskEntity.getID_();
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        JSONObject json=new JSONObject();
        json.put("pId",pId);
        json.put("taskId",taskId);
        json.put("taskName",task.getName());
        json.put("taskDes",task.getDescription());
        System.out.println(json);
        return Result.ok(SUCCESS_CODE,SUCCESS_MSG,json);
    }

    /**
     * @Description delete
     * @Author caodonghui
     * @Date 19:54 2022/9/6
     **/
    @RequestMapping(value = "deleteProcess", method = RequestMethod.POST)
    public Result deleteProcess(@RequestParam String id)
    {
        //?????????????????????????????????????????????????????????????????????????????????????????????????????????
        processEngine.getRepositoryService().deleteDeployment(id, true);
        return Result.ok(SUCCESS_CODE,SUCCESS_MSG,null);
    }

    /**
     * ??????
     * @param pId
     * @return
     */
    @RequestMapping(value = "queryTask", method = RequestMethod.POST)
    public Result queryTask(@RequestParam int pId)
    {
        TaskEntity taskEntity = riceService.selectTaskId(pId);
        if(taskEntity==null)
        {
            return Result.ok(FAILED_CODE_OUT_ERR,FAILED_MSG,"???????????????");
        }
        String taskId = taskEntity.getID_();
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        JSONObject json=new JSONObject();
        json.put("pId",pId);
        json.put("taskId",taskId);
        json.put("taskName",task.getName());
        json.put("taskDes",task.getDescription());
        System.out.println(json);
        return Result.ok(SUCCESS_CODE,SUCCESS_MSG,json);
    }


    /**
     * ??????????????????????????????
     * @param taskId
     * @param content
     * @return
     */
    @RequestMapping(value = "completeTask", method = RequestMethod.POST)
    public Result dealPeopleReq(String taskId, String content) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return Result.ok(FAILED_CODE_OUT_ERR,FAILED_MSG,null);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        taskService.complete(taskId, map);
        return Result.ok(SUCCESS_CODE,SUCCESS_MSG,null);
    }
}

