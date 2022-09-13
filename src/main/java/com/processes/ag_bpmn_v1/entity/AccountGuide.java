package com.processes.ag_bpmn_v1.entity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AccountGuide {
    private String username;
    private Integer pid;
    private Integer son_id;
    private String guide_info;
    /* @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
     private Date createTime;
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
     private Date updateTime;*/
    public AccountGuide(){
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getSonid() {
        return son_id;
    }

    public void setSonid(int son_id) {
        this.son_id = son_id;
    }

    public String getGuideInfo() {
        return guide_info;
    }

    public void setGuideInfo(String guide_info) {
        this.guide_info = guide_info;
    }

    /*public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }*/

    @Override
    public String toString() {
        return "Guide{" +
                "username='" + username + '\'' +
                ", pid='" + pid + '\'' +
                ", son_id='" + son_id + '\'' +
                ", guide_info='" + guide_info + '\'' +
                //", creatTime='" + son_id + '\'' +
                //", updateTime='" + son_id + '\'' +
                '}';
    }

}
