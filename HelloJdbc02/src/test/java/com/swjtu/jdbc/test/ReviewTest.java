package com.swjtu.jdbc.test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

import com.swjtu.jdbc.utils.JdbcUtil;

public class ReviewTest {
	
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
			JdbcUtil.closeStatAndConnAndResultSet(stat, conn, rs);
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
			JdbcUtil.closeStatAndConnAndResultSet(stat, conn, rs);
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
