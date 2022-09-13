package com.processes.ag_bpmn_v1.mapper;

import com.processes.ag_bpmn_v1.entity.Process;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProcessMapper {
    List<Process> queryList();
}
