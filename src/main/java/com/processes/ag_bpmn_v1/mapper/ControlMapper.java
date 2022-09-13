package com.processes.ag_bpmn_v1.mapper;

import com.processes.ag_bpmn_v1.entity.Control;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ControlMapper {

    List<Control> selectPInfoByUsername(String username);

    int deleteProcessByPid(@Param(value = "username") String username, @Param(value = "pid") int pid);

}
