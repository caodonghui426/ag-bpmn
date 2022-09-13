package com.processes.ag_bpmn_v1.service;

import com.processes.ag_bpmn_v1.entity.Process;
import com.processes.ag_bpmn_v1.entity.TaskEntity;

import java.util.List;

public interface ProcessService {

    List<Process> queryList();
}
