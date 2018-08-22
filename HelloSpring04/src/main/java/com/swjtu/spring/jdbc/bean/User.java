package com.swjtu.spring.jdbc.bean;

public class User {
	private String userName;
	private String password;
	private Department department;
	private int deptId;

	public User() {
	}

	public User(String userName, String password, Department department) {
		this.userName = userName;
		this.password = password;
		this.department = department;
	}
	
	public User(String userName, String password, Department department,
			int deptId) {
		this.userName = userName;
		this.password = password;
		this.department = department;
		this.deptId = deptId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password
				+ ", department=" + department + ", deptId=" + deptId + "]";
	}
}
