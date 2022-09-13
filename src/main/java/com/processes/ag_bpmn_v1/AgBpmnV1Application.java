package com.processes.ag_bpmn_v1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.processes.ag_bpmn_v1.mapper")
public class AgBpmnV1Application {

    public static void main(String[] args) {
        SpringApplication.run(AgBpmnV1Application.class, args);
    }

}
