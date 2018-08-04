package com.swjtu.jdbc.test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

import com.swjtu.jdbc.bean.Student;
import com.swjtu.jdbc.utils.JdbcUtils;

public class ReviewTest {
	
	/**
	 * 使用 PreparedStatement 将有效的解决 SQL 注入问题.
	 */
	@Test
	public void testSQLInjection2() {
		String username = "a' OR PASSWORD = ";
		String password = " OR '1'='1";

		String sql = "SELECT * FROM user_tbl WHERE user_name = ? "
				+ "AND password = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery(); // 这里查询失败
			
			if (resultSet.next()) {
				System.out.println("登录成功!");
			} else {
				System.out.println("用户名和密码不匹配或用户名不存在. ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeStatAndConnAndResultSet(preparedStatement, connection, resultSet);
		}
	}

	/**
	 * SQL 注入.
	 */
	@Test
	public void testSQLInjection() {
		String username = "a' OR PASSWORD = ";
		String password = " OR '1'='1";

		String sql = "SELECT * FROM user_tbl WHERE user_name = '" + username
				+ "' AND " + "password = '" + password + "'";
		// sql注入攻击使用的sql： SELECT * FROM user_tbl WHERE user_name = 'a' OR PASSWORD = ' AND password = ' OR '1'='1'
		System.out.println(sql);

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				System.out.println("登录成功!"); // 居然登录成功了。
			} else {
				System.out.println("用户名和密码不匹配或用户名不存在. ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeStatAndConnAndResultSet(statement, connection, resultSet);
		}
	}
	
	/**
	 * 通过预编译sql语句 PreparedStatement 插入学生信息2
	 */
	@Test
	public void addStudent2ByPreparedStatement() {
		Student stu = new Student("pa201408", "pa123456", "pa511090", "p李四", 89, "p78", "pa成都高新区");
		
		String sql = "INSERT INTO exam_student_tbl ( student_num"
															 + ", exam_registration_num"
															 + ", id_card"
															 + ", name"
															 + ", age"
															 + ", grade"
															 + ", loc) " +
					  "VALUES(?,?,?,?,?,?,?)";
		JdbcUtils.updateV2ByPreparedStatement(sql
				, stu.getStudentNum()
				, stu.getExamRegistrationNum()
				, stu.getIdCard()
				, stu.getName()
				, stu.getAge()
				, stu.getGrade()
				, stu.getLoc()
			);
	}
	
	/**
	 * 2、PreparedStatement： 是 Statement的子接口，可以传入带占位符的sql语句，并且提供了补充占位符变量的方法：
		2.1）使用 PreparedStatement：
		step1）创建 PreparedStatement；
		String sql = "insert into table values(?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		step2）调用 PreparedStatement 的 setXXX(int index, Object val) 设置占位符的值；
		索引值index从1开始
		step3）执行 sql语句：executeQuery() 进行查询 或 executeUpdate进行增删改 操作
		注意：执行时不再需要传入 sql 语句；
	 * @throws Exception 
	 */
	@Test
	public void testPreparedStatement()  {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		try {
			Student stu = new Student();
			String sql = "INSERT INTO exam_student_tbl ( student_num"
					 + ", exam_registration_num"
					 + ", id_card"
					 + ", name"
					 + ", age"
					 + ", grade"
					 + ", loc) VALUES(?, ?, ?, ?, ?, ?, ?)";
			
			conn = getConnection();
			stat = conn.prepareStatement(sql);
			stat.setString(1, "23412");
			stat.setString(2, "987888");
			stat.setString(3, "34234");
			stat.setString(4, "王五");
			stat.setInt(5, 100);
			stat.setString(6, "98.5");
			stat.setString(7, "成都天府新区");
			
			int updRows = stat.executeUpdate();
			System.out.println("更新行数 = " + updRows);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeStatAndConnAndResultSet(stat, conn, rs);
		}
	}
	
	/**
	 * 查询student
	 * @throws Exception 
	 */
	@Test
	public void getStudent()  {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			Student stu = new Student();
			String sql = "SELECT student_num, exam_registration_num, id_card, name, age, grade, loc FROM exam_student_tbl";
			conn = getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeStatAndConnAndResultSet(stat, conn, rs);
		}
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
