package com.atguigu.crud.bean;

public class Department {
    private String rcrdId;

    private String deptId;

    private String deptName;

    public String getRcrdId() {
        return rcrdId;
    }

    public void setRcrdId(String rcrdId) {
        this.rcrdId = rcrdId == null ? null : rcrdId.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }
}