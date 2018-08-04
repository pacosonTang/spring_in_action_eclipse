package com.swjtu.jdbc.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.Driver;
import com.swjtu.jdbc.test.JdbcTest01;

/**
 * 操作jdbc的工具类-封装了一些工具方法
 * @author pacoson
 */
public class JdbcUtils {
	
	
	
	/**
	 * 通用的更新方法：insert, update, delete
	 * 版本1
	 * @param sql
	 */
	public static void updateV2ByPreparedStatement(String sql, Object... objs) {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			// 1.获取数据库连接
			conn = JdbcUtils.getConnection();
			// 4.执行插入
			// 4.1 获取操作sql语句的statement对象； 
//			调用Connection的  createStatement() 方法来获取
			stat = conn.prepareStatement(sql);
			int i = 1;
			for (Object obj : objs) {
				stat.setObject(i++, obj);
			}
			System.out.println("连接=" + conn + "，语句 = " + stat);
			// 4.2 调用statement对象的 executeUpdate(sql) 执行sql语句进行插入
			int updateRows = stat.executeUpdate();
			System.out.println("更新记录行数：" + updateRows);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeStatAndConn(stat, conn);
		}
	}
	
	/**
	 * 通用的更新方法：insert, update, delete
	 * 版本1
	 * @param sql
	 */
	public static void updateV1(String sql) {
		Connection conn = null;
		Statement stat = null;
		try {
			// 1.获取数据库连接
			conn = JdbcUtils.getConnection();
			// 4.执行插入
			// 4.1 获取操作sql语句的statement对象； 
//			调用Connection的  createStatement() 方法来获取
			stat = conn.createStatement();
			System.out.println("连接=" + conn + "，语句 = " + stat);
			// 4.2 调用statement对象的 executeUpdate(sql) 执行sql语句进行插入
			int updateRows = stat.executeUpdate(sql);
			System.out.println("更新记录行数：" + updateRows);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeStatAndConn(stat, conn);
		}
	}
	
	/**
	 * 获取连接的方法。
	 * 通过读取配置文件从数据库服务器获取一个连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		
		// 读取类路径下的 jdbc.properties 文件并将其封装到 Properties中：
		InputStream in = JdbcTest01.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties props = new Properties();
		props.load(in);
		/* 通过反射获取驱动器Driver */
		Driver driver = (Driver) Class.forName(props.getProperty("driver")).newInstance();
		/* 获取数据库连接  */
		Connection conn = driver.connect(props.getProperty("url"), props);
		return conn;
	}
	
	/**
	 * 关闭数据库语句 statement 和 数据库连接 conn 和 结果集 ResultSet
	 * @param stat
	 * @param conn
	 */
	public static void closeStatAndConnAndResultSet(Statement stat, Connection conn, ResultSet rs) {
		if (null != rs) {
			try {
				rs.close(); // 关闭ResultSet对象
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if (null != stat) {
			try {
				stat.close(); // 关闭statement对象
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if (null != conn) {
			try {
				conn.close(); // 关闭连接
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 关闭数据库语句 statement 和 数据库连接 conn 
	 * @param stat
	 * @param conn
	 */
	public static void closeStatAndConn(Statement stat, Connection conn) {
		if (null != stat) {
			try {
				stat.close(); // 关闭statement对象
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if (null != conn) {
			try {
				conn.close(); // 关闭连接
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
