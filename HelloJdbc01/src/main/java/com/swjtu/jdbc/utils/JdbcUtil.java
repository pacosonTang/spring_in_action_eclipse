package com.swjtu.jdbc.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.Driver;
import com.swjtu.jdbc.test.JdbcTest01;

/**
 * 操作jdbc的工具类-封装了一些工具方法
 * @author pacoson
 */
public class JdbcUtil {
	
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
}
