package com.swjtu.jdbc.bean;

/**
 * 考生类
 * @author Tang Rone
 * @date 2018年8月1日
 *
 */
public class Student {
	/*-- 新建考生表
	 -- 新建考生表
	drop table if exists exam_student_tbl;
	create table exam_student_tbl (
		rcrd_id varchar(20) primary key comment '记录编号',
		student_num varchar(20) default '' comment '学生号',
		exam_registration_num varchar(20) default '' comment '准考证号',
		id_card varchar(20) default '' comment '身份证号码',
		name varchar(20) default '' comment '姓名',
		age int default 0 comment '年龄',
		grade varchar(20) default '' comment '年级', 
		loc varchar(50) default '' comment '考试位置'
	) engine=InnoDB default charset=utf8 comment='考生表'
	;*/
	private String studentNum;
	private String examRegistrationNum;
	private String idCard;
	private String name;
	private int age;
	private String grade;
	private String loc;
	
	public Student(String studentNum,
			String examRegistrationNum, String idCard, String name, int age,
			String grade, String loc) {
		this.studentNum = studentNum;
		this.examRegistrationNum = examRegistrationNum;
		this.idCard = idCard;
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.loc = loc;
	}
	
	
	
	public Student() {
	}

	@Override
	public String toString() {
		return "Student [studentNum=" + studentNum
				+ ", examRegistrationNum=" + examRegistrationNum + ", idCard="
				+ idCard + ", name=" + name + ", age=" + age + ", grade="
				+ grade + ", loc=" + loc + "]";
	}

	public String getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}
	public String getExamRegistrationNum() {
		return examRegistrationNum;
	}
	public void setExamRegistrationNum(String examRegistrationNum) {
		this.examRegistrationNum = examRegistrationNum;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
	
}
