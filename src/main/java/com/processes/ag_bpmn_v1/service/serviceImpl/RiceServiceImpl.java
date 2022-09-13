package com.processes.ag_bpmn_v1.service.serviceImpl;

import com.processes.ag_bpmn_v1.entity.TaskEntity;
import com.processes.ag_bpmn_v1.mapper.RiceMapper;
import com.processes.ag_bpmn_v1.service.RiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiceServiceImpl implements RiceService {


    @Autowired
    private RiceMapper riceMapper;

    public TaskEntity selectTaskId(int pId){
        return riceMapper.selectTaskId(pId);
    }






}
