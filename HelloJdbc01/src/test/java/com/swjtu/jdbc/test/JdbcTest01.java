package com.swjtu.jdbc.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

import com.mysql.jdbc.Driver;

public class JdbcTest01 {
	
	@Test
	public void testGetConnectionByDriverManager() throws Exception {
		System.out.println("testGetConnectionByDriverManager conn = " + getConnectionByDriverManager());
	}
	/**
	 * 通过 DriverManager获取 数据库连接 Connection
	 * @throws Exception 
	 */
	public static Connection getConnectionByDriverManager() throws Exception {

		/*加载数据库配置文件*/
		InputStream in = JdbcTest01.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties info = new Properties();
		info.load(in);
		
		Class.forName(info.getProperty("driver"));
		Connection conn = DriverManager.getConnection(info.getProperty("url"), info);
		return conn;
	}
	/**
	 * DriverManager 是驱动管理类，通过 DriverManager 获取 数据库连接：
	 * 1、可以通过重载的 getConnection() 获取数据库连接；比较方便；
	 * 2、DriverManager可以同时管理多个驱动程序：如注册了多个数据库连接，则调用 getConnection方法
	 * 时传入的参数不同，则返回不同的数据库连接。
	 * @throws Exception 
	 */
	@Test
	public void testDriverManager() throws Exception {
		// 读取类路径下的 jdbc.properties 文件并将其封装到 Properties中：
		InputStream in = JdbcTest01.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties props = new Properties();
		props.load(in);
		
		/*加载数据库驱动程序（加载多个驱动程序）*/
		Class.forName(props.getProperty("driver"));
//		Class.forName("oracle 的驱动名");
//		Class.forName("mysql 的驱动名");
		/*通过DriverManager获取数据库连接*/
		Connection conn = DriverManager.getConnection(props.getProperty("url"), props);
		System.out.println("DriverManager获取的连接 " + conn);
	}
	
	/**
	 * Driver 是一个接口:
	 * 数据库厂商必须提供Driver实现类的接口， 
	 * 能从其中获取数据库连接。
	 * @throws SQLException 
	 */
	@Test
	public void testDriver() throws Exception {

		/* 获取数据库连接  */
		Connection conn = getConnection();
		System.out.println("通过反射 获取的连接 " + conn);
	}
	
	/**
	 * 编写一个通用方法： 在不修改源程序的情况下，可以获取任何数据库的连接
	 * 解决方法：把数据库驱动Driver实现类的全类名， url, user, password 放入一个配置文件中， 
	 * 通过修改配置文件的方式实现和具体的数据库解耦。
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
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
