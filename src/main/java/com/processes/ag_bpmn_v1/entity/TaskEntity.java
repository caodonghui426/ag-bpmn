package com.processes.ag_bpmn_v1.entity;

public class TaskEntity {

    private String ID_;
    private String PROC_INST_ID_;

    @Override
    public String toString() {
        return "TaskEntity{" +
                "ID_=" + ID_ +
                ", PROC_INST_ID_=" + PROC_INST_ID_ +
                '}';
    }


    public String getID_() {
        return ID_;
    }

    public void setID_(String ID_) {
        this.ID_ = ID_;
    }

    public String getPROC_INST_ID_() {
        return PROC_INST_ID_;
    }

    public void setPROC_INST_ID_(String PROC_INST_ID_) {
        this.PROC_INST_ID_ = PROC_INST_ID_;
    }
}
