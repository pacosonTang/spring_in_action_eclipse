package com.swjtu.crud.bean;

public class Department {
    private String rcrdId;
    private String deptId;
    private String deptName;

    public Department() {}

	public Department(String rcrdId, String deptId, String deptName) {
		this.rcrdId = rcrdId;
		this.deptId = deptId;
		this.deptName = deptName;
	}

	public String getRcrdId() {
		return rcrdId;
	}

	public void setRcrdId(String rcrdId) {
		this.rcrdId = rcrdId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "Department [rcrdId=" + rcrdId + ", deptId=" + deptId
				+ ", deptName=" + deptName + "]";
	}
}