package com.swjtu.spring.jdbc.bean;

public class Department {
	private String id;
	private String deptName;
	
	public Department() {
	}

	public Department(String id, String deptName) {
		this.id = id;
		this.deptName = deptName;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", deptName=" + deptName + "]";
	}
}
