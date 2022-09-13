package com.processes.ag_bpmn_v1.mapper;
import com.processes.ag_bpmn_v1.entity.AccountGuide;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Mapper
public interface AccountGuideMapper {

    //1.通过sonid查询用户信息
    AccountGuide getAccountGuide(@Param(value = "username") String username, @Param(value = "pid") int pid , @Param(value = "son_id") int son_id);
    //2.通过id删除用户信息
    int delete(@Param(value = "username") String username, @Param(value = "pid") int pid , @Param(value = "son_id") int son_id);
    //3.更改用户信息
    int update(@Param(value = "username") String username, @Param(value = "pid") int pid , @Param(value = "son_id") int son_id, @Param(value = "guide_info") String guide_info);
    //4.插入用户信息
    int save(@Param(value = "username") String username, @Param(value = "pid") int pid , @Param(value = "son_id") int son_id, @Param(value = "guide_info") String guide_info);
    //5.查询所有用户信息
    List<AccountGuide> selectAll(String username, int pid , int son_id,  String guide_info);

}
