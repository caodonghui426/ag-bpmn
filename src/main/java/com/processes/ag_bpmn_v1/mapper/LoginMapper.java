package com.processes.ag_bpmn_v1.mapper;

import com.processes.ag_bpmn_v1.entity.Login;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    Login selectOne(String username);


}
