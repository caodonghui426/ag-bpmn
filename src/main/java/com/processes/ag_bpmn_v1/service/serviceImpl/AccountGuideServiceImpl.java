package com.processes.ag_bpmn_v1.service.serviceImpl;

import com.processes.ag_bpmn_v1.entity.AccountGuide;
import com.processes.ag_bpmn_v1.entity.Control;
import com.processes.ag_bpmn_v1.mapper.AccountGuideMapper;
import com.processes.ag_bpmn_v1.service.AccountGuideService;
import com.processes.ag_bpmn_v1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.processes.ag_bpmn_v1.util.constant.*;
import static com.processes.ag_bpmn_v1.util.constant.SUCCESS_CODE;
import static com.processes.ag_bpmn_v1.util.constant.SUCCESS_MSG;

@Service
public class AccountGuideServiceImpl implements AccountGuideService{
    @Autowired
    private AccountGuideMapper accountguideMapper;

    //查
    @Override
    public Result getAccountGuide(String username,int pid ,int son_id){

        accountguideMapper.getAccountGuide(username,pid,son_id);

        return Result.ok(SUCCESS_CODE,SUCCESS_MSG,accountguideMapper.getAccountGuide(username,pid,son_id));
    }

    //删
    @Override
    public Result delete(String username,int pid ,int son_id){

        accountguideMapper.delete(username,pid,son_id);

        return Result.ok(SUCCESS_CODE,SUCCESS_MSG,"删除成功");
    }
    @Override
    public Result update(String username,int pid ,int son_id, String guide_info){
/*
            List<AccountGuide> lists = accountguideMapper.selectAll(username, pid, son_id, guide_info);
            Map<String, Object[]> map = new HashMap<>();
            String[] unameList = new String[lists.size()];
            Integer[] pidList = new Integer[lists.size()];
            String[] guideList = new String[lists.size()];
            Integer[] sonidList = new Integer[lists.size()];
            //Integer[] pidList = new Integer[lists.size()];
        for(int i=0;i<lists.size();i++){
            if(i==6){break;}

            pidList[i]= lists.get(i).getPid();
            unameList[i]=lists.get(i).getUsername();
            guideList[i]=lists.get(i).getGuideInfo();
            sonidList[i]=lists.get(i).getSonid();
        }


            map.put("username", unameList);
            map.put("processId",pidList);
            map.put("sonId",sonidList);
            map.put("newInfos", guideList);
           // Map<String, Integer> intmap = new HashMap<>();
            //Integer[] sonidList = new Integer[lists.size()];
            //Integer[] pidList = new Integer[lists.size()];
            //intmap.put("processId", pid);
           // intmap.put("sonId", son_id);

*/

        int a;
        a = accountguideMapper.update(username,pid,son_id,guide_info);
        if(a==1) {

            return Result.ok(SUCCESS_CODE, SUCCESS_MSG,"更新成功" /*accountguideMapper.update(username,pid,son_id,guide_info)*/);
        }else{
            return Result.err(FAILED_CODE, FAILED_MSG, "更新失败"/*accountguideMapper.update(username,pid,son_id,guide_info)*/);
        }
    }
    @Override
    public Result save(String username,int pid ,int son_id, String guide_info){

        //return Result.ok(SUCCESS_CODE,SUCCESS_MSG,accountguideMapper.save(username,pid,son_id,guide_info));
        int a;
        a = accountguideMapper.save(username,pid,son_id,guide_info);
        if(a==1) {

            return Result.ok(SUCCESS_CODE, SUCCESS_MSG,"新增成功" /*accountguideMapper.update(username,pid,son_id,guide_info)*/);
        }else{
            return Result.err(FAILED_CODE, FAILED_MSG, "新增失败"/*accountguideMapper.update(username,pid,son_id,guide_info)*/);
        }




    }





}
