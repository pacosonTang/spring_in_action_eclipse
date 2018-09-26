package com.atguigu.crud.bean;

public class Employee {
    private String rcrdId;
    private String empId;
    private String empName;
    private String gender;
    private String email;
    private String deptRcrdId;

    public Employee(){}
    
    public Employee(String rcrdId, String empId, String empName, String gender,
			String email) {
		this.rcrdId = rcrdId;
		this.empId = empId;
		this.empName = empName;
		this.gender = gender;
		this.email = email;
	}
    
    public Employee(String rcrdId, String empId, String empName, String gender,
			String email, String deptRcrdId) {
		this.rcrdId = rcrdId;
		this.empId = empId;
		this.empName = empName;
		this.gender = gender;
		this.email = email;
		this.deptRcrdId = deptRcrdId;
	}

	public String getRcrdId() {
        return rcrdId;
    }

    public void setRcrdId(String rcrdId) {
        this.rcrdId = rcrdId == null ? null : rcrdId.trim();
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId == null ? null : empId.trim();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getDeptRcrdId() {
        return deptRcrdId;
    }

    public void setDeptRcrdId(String deptRcrdId) {
        this.deptRcrdId = deptRcrdId == null ? null : deptRcrdId.trim();
    }

	@Override
	public String toString() {
		return "Employee [rcrdId=" + rcrdId + ", empId=" + empId + ", empName="
				+ empName + ", gender=" + gender + ", email=" + email
				+ ", deptRcrdId=" + deptRcrdId + "]";
	}
}