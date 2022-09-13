package com.processes.ag_bpmn_v1.controller;

import com.alibaba.fastjson.JSONObject;
import com.processes.ag_bpmn_v1.util.Result;
import org.apache.commons.io.FileUtils;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.processes.ag_bpmn_v1.util.constant.*;

@RestController
@RequestMapping("/corn")
public class CornController {

    /**
     * 获取本地xml文件
     * @return
     * @throws IOException
     */

    public String getXml() throws IOException {
        File file = ResourceUtils.getFile("classpath:corn.bpmn20.xml");
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        return content;
    }

    /**
     * @Description 部署流程，接收前端传的xml文件（流程）
     * @Author caodonghui
     * @Date 18:09 2022-07-27
     **/
    @RequestMapping(value = "addProcess",method = RequestMethod.POST)
    public Result addFile(@RequestBody JSONObject json) throws IOException {
        json.put("fileName","corn");
        json.put("content",getXml());

        String fileName = (String) json.get("fileName");
        String content = (String) json.get("content");
        //加上.bpmn20.xml后缀
        fileName += SUFFIX;
        //获取ProcessEngine对象
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        //获取 RepositoryService对象
        RepositoryService service = engine.getRepositoryService();
        //获取部署的deploy对象，完成流程的部署操作
        Deployment deploy = service.createDeployment().name("corn").addString(fileName,content).deploy();
        System.out.println((deploy.getId() + "    " + deploy.getName()));
        return Result.ok(SUCCESS_CODE,SUCCESS_MSG,deploy.getName());
    }

    /**
     * @Description TODO
     * @Author caodonghui
     * @Date 21:40 2022-07-27
     **/
    public Result startProcess(@RequestParam String pName) {
        return Result.ok(SUCCESS_CODE,SUCCESS_MSG,pName);
    }

}
