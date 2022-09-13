package com.processes.ag_bpmn_v1.service;

import com.processes.ag_bpmn_v1.util.Result;

public interface LoginService  {

    Result toLogin(String userName, String password);

}
