package com.processes.ag_bpmn_v1.service;
import com.processes.ag_bpmn_v1.entity.AccountGuide;
import com.processes.ag_bpmn_v1.util.Result;
import org.springframework.web.bind.annotation.RequestParam;

public interface AccountGuideService {
    Result getAccountGuide(String username,int pid ,int son_id);
    Result delete(String username,int pid ,int son_id);
    Result update(String username,int pid ,int son_id, String guide_info);
    Result save(String username,int pid ,int son_id, String guide_info);


}
