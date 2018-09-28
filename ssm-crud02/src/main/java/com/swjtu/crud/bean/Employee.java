package com.swjtu.crud.bean;

public class Employee {
	private String rcrdId;
	private String empId;
	private String empName;
	private String gender;
	private String email;
	private String deptRcrdId;
	private String empState;
	
	public Employee() {}
	
	public Employee(String rcrdId, String empId, String empName, String gender,
			String email, String deptRcrdId, String empState) {
		this.rcrdId = rcrdId;
		this.empId = empId;
		this.empName = empName;
		this.gender = gender;
		this.email = email;
		this.deptRcrdId = deptRcrdId;
		this.empState = empState;
	}

	public String getRcrdId() {
		return rcrdId;
	}

	public void setRcrdId(String rcrdId) {
		this.rcrdId = rcrdId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDeptRcrdId() {
		return deptRcrdId;
	}

	public void setDeptRcrdId(String deptRcrdId) {
		this.deptRcrdId = deptRcrdId;
	}

	public String getEmpState() {
		return empState;
	}

	public void setEmpState(String empState) {
		this.empState = empState;
	}

	@Override
	public String toString() {
		return "Employee [rcrdId=" + rcrdId + ", empId=" + empId + ", empName="
				+ empName + ", gender=" + gender + ", email=" + email
				+ ", deptRcrdId=" + deptRcrdId + ", empState=" + empState + "]";
	}
}