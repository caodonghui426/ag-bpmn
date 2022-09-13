package com.processes.ag_bpmn_v1.service;

import com.processes.ag_bpmn_v1.util.Result;

public interface ControlService {

    Result getAllCount(String username);

    Result getRecentByUsername(String username);

    Result getAllProcessByUsername(String username);

    Result delProcessById(String username, int pid);
}
