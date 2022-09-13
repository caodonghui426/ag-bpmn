package com.processes.ag_bpmn_v1.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.processes.ag_bpmn_v1.entity.Process;
import com.processes.ag_bpmn_v1.entity.TaskEntity;
import com.processes.ag_bpmn_v1.service.ControlService;
import com.processes.ag_bpmn_v1.service.ProcessService;
import com.processes.ag_bpmn_v1.util.AjaxResult;
import com.processes.ag_bpmn_v1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.processes.ag_bpmn_v1.util.constant.*;
import static com.processes.ag_bpmn_v1.util.constant.FAILED_MSG;

@RestController
@RequestMapping(value = "/process")
public class ProcessController {

    @Autowired
    private ControlService controlService;

    @Autowired
    private ProcessService processService;
    /**
    * @Description  获取流程列表
    * @Author  jiangyongtao
    * @Date
    */
    @RequestMapping(value = "/showList", method = RequestMethod.POST)
    public Result getAllProcessList(@RequestParam String userName) {
        return controlService.getAllProcessByUsername(userName);
    }


    /**
     * @Description  删除某一个流程
     * @Author  jiangyongtao
     * @Date
     */
    @RequestMapping(value = "/delProcess", method = RequestMethod.POST)
    public Result getProcessDetail(@RequestParam int  processId,@RequestParam String userName) {
        return controlService.delProcessById(userName,processId);
    }

    /**
     * @Description 获取流程列表v2
     * @Author caodonghui
     * @Date 14:31 2022/8/20
     **/
    @RequestMapping(value = "/procdefList",method = RequestMethod.POST)
    public String procdefList() {
        //获取流程定义实体
        List<Process> processList = processService.queryList();
        System.out.println(processList);
        return JSON.toJSONString(AjaxResult.success("查询成功",processList));
    }
}
