package com.processes.ag_bpmn_v1.controller;


import com.processes.ag_bpmn_v1.service.ControlService;
import com.processes.ag_bpmn_v1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "control")
public class controlController {


    @Autowired
    private ControlService controlService;


    /**
    * @Description  获取控制台数字展示
    * @Author  jiangyongtao
    * @Date
    */
    @RequestMapping(value = "/showCount", method = RequestMethod.POST)
    public Result getAllCountNum(@RequestParam String userName) {
        return controlService.getAllCount(userName);
    }

    /**
     * @Description  获取最近编辑流程信息展示
     * @Author  jiangyongtao
     * @Date
     */
    @RequestMapping(value = "/showRecent", method = RequestMethod.POST)
    public Result getAllRecent(@RequestParam String userName) {
        return controlService.getRecentByUsername(userName);
    }
}
