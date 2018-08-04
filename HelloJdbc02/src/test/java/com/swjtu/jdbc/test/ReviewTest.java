package com.swjtu.jdbc.test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

import com.swjtu.jdbc.bean.Student;
import com.swjtu.jdbc.utils.JdbcUtils;

public class ReviewTest {
	
	/**
	 * 查询student
	 * @throws Exception 
	 */
	@Test
	public void getStudent() throws Exception {
		Student stu = new Student();
		String sql = "SELECT student_num, exam_registration_num, id_card, name, age, grade, loc FROM exam_student_tbl";
		Connection conn = getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		while(rs.next()) {
			stu.setStudentNum(rs.getObject(1).toString());
			stu.setExamRegistrationNum(rs.getObject(2).toString());
			stu.setIdCard(rs.getObject(3).toString());
			stu.setName(rs.getObject(4).toString());
			stu.setAge(Integer.valueOf(rs.getObject(5).toString()));
			stu.setGrade(rs.getObject(6).toString());
			stu.setLoc(rs.getObject(7).toString());
			System.out.println(stu);
		}
		JdbcUtils.closeStatAndConnAndResultSet(stat, conn, rs);
	}
	/**
	 * 插入学生信息
	 */
	@Test
	public void addStudent() {
		Student stu = new Student("a201408", "a123456", "a511090", "李四", 89, "78", "a成都高新区");
		
		String sql = "INSERT INTO exam_student_tbl ( student_num"
															 + ", exam_registration_num"
															 + ", id_card"
															 + ", name"
															 + ", age"
															 + ", grade"
															 + ", loc) " +
					  "VALUES('" + stu.getStudentNum() + "','"
					            + stu.getExamRegistrationNum() + "','"
					            + stu.getIdCard() + "', '"
					            + stu.getName() + "', '"
					            + stu.getAge()  + "', '"
					            + stu.getGrade() + "', '"
					            + stu.getLoc() + "')";
		JdbcUtils.updateV1(sql);
	}
	/**
	 * 1、statement 用于修改操作 update
	 */
	@Test
	public void testUpdateStatement() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			// 获取id = 20063 的emp记录并打印
			String sql = "update emp_tbl set dept_id = 3 where id > '20000'";
			int updRows = stat.executeUpdate(sql);
			System.out.println("更新行数 = " + updRows);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeStatAndConnAndResultSet(stat, conn, rs);
		}
	}
	
	/**
	 * 1、statement 是用于查询操作select；
	 */
	@Test
	public void testSelectStatement() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			// 获取id = 20063 的emp记录并打印
			String sql = "SELECT id, last_name, gender, email, dept_id, status FROM emp_tbl where dept_id = '1'";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				String lastName = rs.getString(2);
				String gender = rs.getString(3);
				String email = rs.getString(4);
				String deptId = rs.getString(5);
				System.out.println("id=" + id + ", lastName=" + lastName + ", gender = " + gender 
						+ ", email = " + email + ", deptId = " + deptId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeStatAndConnAndResultSet(stat, conn, rs);
		}
	}
	
	/**
	 * 获取数据库连接 
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		InputStream is = ReviewTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties info= new Properties();
		info.load(is);
		
		Connection conn = DriverManager.getConnection(info.getProperty("url"), info);
		System.out.println("conn = " + conn);
		return conn;
	}
}
