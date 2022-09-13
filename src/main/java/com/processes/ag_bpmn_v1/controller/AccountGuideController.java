package com.processes.ag_bpmn_v1.controller;

import com.processes.ag_bpmn_v1.entity.AccountGuide;
import com.processes.ag_bpmn_v1.service.AccountGuideService;
import com.processes.ag_bpmn_v1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "arg")
public class AccountGuideController {

    @Autowired
    private AccountGuideService accountguideService;

    //通过sonid得到用户信息
    @RequestMapping(value = "/selectInfos", method = RequestMethod.POST)
    public Result getAccountGuide(@RequestParam String userName,@RequestParam int processId ,@RequestParam int sonId) {
        return accountguideService.getAccountGuide(userName,processId,sonId);

    }
    //通过sonid删除用户信息
    @RequestMapping(value = "/deleteInfos", method = RequestMethod.POST)
    public Result delete(@RequestParam String userName,@RequestParam int processId ,@RequestParam int sonId) {
        return accountguideService.delete(userName,processId,sonId);
    }
    //更改用户信息
    @RequestMapping(value = "/updateInfos", method = RequestMethod.POST)
    public Result update(@RequestParam String userName,@RequestParam int processId ,@RequestParam int sonId, @RequestParam String newInfos) {
        return accountguideService.update(userName,processId,sonId,newInfos);
    }
    //新增用户信息
    @RequestMapping(value = "/insertInfos", method = RequestMethod.POST)
    public Result save(@RequestParam String userName,@RequestParam int processId ,@RequestParam int sonId, @RequestParam String newInfos) {
        return accountguideService.save(userName,processId,sonId,newInfos);
    }


}
