package com.processes.ag_bpmn_v1.service.serviceImpl;

import com.processes.ag_bpmn_v1.entity.Control;
import com.processes.ag_bpmn_v1.mapper.ControlMapper;
import com.processes.ag_bpmn_v1.service.ControlService;
import com.processes.ag_bpmn_v1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.processes.ag_bpmn_v1.util.constant.SUCCESS_CODE;
import static com.processes.ag_bpmn_v1.util.constant.SUCCESS_MSG;

@Service
public class ControlServiceImpl implements ControlService {


    @Autowired
    private ControlMapper controlMapper;

    @Override
    public Result getAllCount(String username){
        List<Control> lists = controlMapper.selectPInfoByUsername(username);
        Map<String,Integer> map  = new HashMap<>();
        map.put("processCount",lists.size());
        return Result.ok(SUCCESS_CODE,SUCCESS_MSG,map);
    }

    @Override
    public Result getRecentByUsername(String username){
        List<Control> lists = controlMapper.selectPInfoByUsername(username);
        Map<String,String[]> map  = new HashMap<>();
        String[] pnameList = new String[6];
        for(int i=0;i<lists.size();i++){
            if(i==6){break;}
            pnameList[i]=lists.get(i).getPname();
        }
        map.put("processName",pnameList);
        return Result.ok(SUCCESS_CODE,SUCCESS_MSG,map);
    }


    @Override
    public Result getAllProcessByUsername(String username){
        List<Control> lists = controlMapper.selectPInfoByUsername(username);
        Map<String,Object[]> map  = new HashMap<>();
        String[] pnameList = new String[6];
        Integer[] pidList = new Integer[6];
        for(int i=0;i<lists.size();i++){
            if(i==6){break;}
            pidList[i]= lists.get(0).getPid();
            pnameList[i]=lists.get(i).getPname();
        }
        map.put("processId",pidList);
        map.put("processName",pnameList);
        return Result.ok(SUCCESS_CODE,SUCCESS_MSG,map);
    }

    @Override
    public Result delProcessById(String username,int pid){
        controlMapper.deleteProcessByPid(username,pid);
        return Result.ok(SUCCESS_CODE,SUCCESS_MSG,null);
    }





}
