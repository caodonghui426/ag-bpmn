package com.processes.ag_bpmn_v1.service.serviceImpl;

import com.processes.ag_bpmn_v1.entity.Login;
import com.processes.ag_bpmn_v1.mapper.LoginMapper;
import com.processes.ag_bpmn_v1.service.LoginService;
import com.processes.ag_bpmn_v1.util.Result;
import liquibase.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.processes.ag_bpmn_v1.util.constant.*;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private  LoginMapper loginMapper;

    @Override
    public Result toLogin(String userName, String password){
        if (StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)){
            return Result.err(FAILED_CODE,FAILED_MSG,null);
        }
        Login user = loginMapper.selectOne(userName);
        if(user==null||!user.getPassword().equals(password)){
            return Result.ok(FAILED_CODE,FAILED_MSG,null);
        }
        return Result.err(SUCCESS_CODE,SUCCESS_MSG,user);
    }



}
