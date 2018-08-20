package com.swjtu.spring.jdbc.bean;

public class User {
	private String userName;
	private String password;
	private Department department;

	public User() {
	}

	public User(String userName, String password, Department department) {
		this.userName = userName;
		this.password = password;
		this.department = department;
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

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password
				+ ", department=" + department + "]";
	}
}
