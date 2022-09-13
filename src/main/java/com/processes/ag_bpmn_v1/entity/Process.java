package com.processes.ag_bpmn_v1.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @ClassName Process
 * @Description TODO
 * @Author 曹栋辉
 * @Date 2022/8/20 17:05
 * @Version 1.0
 **/
public class Process {

    private String key;

    private String name;

    private String version;

    private Integer rev;

    private String category;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date deployTime;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDeployTime() {
        return deployTime;
    }

    public void setDeployTime(Date deployTime) {
        this.deployTime = deployTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", rev=" + rev +
                ", category='" + category + '\'' +
                ", deployTime=" + deployTime +
                '}';
    }
}
