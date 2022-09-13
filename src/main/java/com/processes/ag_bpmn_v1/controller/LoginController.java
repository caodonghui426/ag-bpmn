package com.processes.ag_bpmn_v1.controller;

import com.processes.ag_bpmn_v1.service.LoginService;
import com.processes.ag_bpmn_v1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class LoginController {


    @Autowired
    private LoginService loginService;

    /**
    * @Description 用户登录(没有权限相关 仅基础的登录)
    * @Author  jiangyongtao
    * @Date
    */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result toLogin(@RequestParam String userName, @RequestParam String password) {
        return loginService.toLogin(userName, password);
    }


}
