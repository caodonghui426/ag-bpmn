package com.processes.ag_bpmn_v1;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.flowable.engine.repository.ProcessDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class AgBpmnV1ApplicationTests {

    @Test
    void contextLoads() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //传入参数
        String date="2022-07-03";
        int age=10;

        HttpGet httpGet = new HttpGet("http://localhost:7666/Dmn/get?date="+date+"&age="+age);
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);
        HttpEntity responseEntity = response.getEntity();
        if (responseEntity != null) {
            System.out.println("响应状态为:" + response.getStatusLine());
            JSONObject json=JSONObject.parseObject(EntityUtils.toString(responseEntity));
            System.out.println("水层高度："+json.get("waterHeight"));
            System.out.println("执行动作："+json.get("action"));
        }
    }

    /**
     * 查看流程相关定义
     */
    @Test
    public void testDeployQuery(){
        ProcessEngineConfiguration cfg = new StandaloneInMemProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://localhost:3306/ag_bpmn_v1?useUnicode=true&useSSL=false&characterEncoding=utf-8&serverTimezone=CTT&nullCatalogMeansCurrent=true")
                .setJdbcUsername("root")
                .setJdbcPassword("010426")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        //获取流程对象
        ProcessEngine processEngine = cfg.buildProcessEngine();
        //部署流程 获取RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //获取流程定义对象
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId("22501")
                .singleResult();
        System.out.println("processDefinition.getId() = " + processDefinition.getId());
        System.out.println("processDefinition.getName() = " + processDefinition.getName());
        System.out.println("processDefinition.getDeploymentId() = " + processDefinition.getDeploymentId());
        System.out.println("processDefinition.getDescription() = " + processDefinition.getDescription());
    }

}
