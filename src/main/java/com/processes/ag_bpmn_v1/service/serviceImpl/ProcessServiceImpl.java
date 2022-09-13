package com.processes.ag_bpmn_v1.service.serviceImpl;

import com.processes.ag_bpmn_v1.entity.Process;
import com.processes.ag_bpmn_v1.entity.TaskEntity;
import com.processes.ag_bpmn_v1.mapper.ProcessMapper;
import com.processes.ag_bpmn_v1.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProcessServiceImpl
 * @Description TODO
 * @Author 曹栋辉
 * @Date 2022/8/20 15:26
 * @Version 1.0
 **/
@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessMapper processMapper;


    @Override
    public List<Process> queryList() {
        return processMapper.queryList();
    }
}
