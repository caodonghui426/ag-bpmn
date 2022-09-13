package com.processes.ag_bpmn_v1.mapper;

import com.processes.ag_bpmn_v1.entity.Login;
import com.processes.ag_bpmn_v1.entity.TaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RiceMapper {

    TaskEntity selectTaskId(int pId);


}
