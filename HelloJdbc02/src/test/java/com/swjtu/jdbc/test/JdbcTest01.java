package com.swjtu.jdbc.test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

import com.mysql.jdbc.Driver;
import com.swjtu.jdbc.utils.JdbcUtil;

public class JdbcTest01 {
	
	/**
	 * ResultSet:结果集，封装了使用 jdbc 进行查询的结果。
	 * 1、调用 statement.executeQuery(sql) 可以得到结果集；
	 * 2、ResultSet 返回的实际上就是一张数据表。有一个指针指向数据库表的第一行的前面；
	 * 可以调用 next() 方法检测是否有下一行。若有则返回true且指针下移，否则返回false。
	 * 相当于 Iterator.hasNext 和 next 方法的结合体
	 * 3、当指针定位到一行时，可以通过调用 getXXX(index) 或 getXXX(columnName) 获取每列的值。
	 * 如：getInt(1), getString("name")
	 * 4、ResultSet 当然也需要进行关闭。
	 */
	@Test
	public void testResultSet() {
		// 获取id = 20063 的emp记录并打印
		String sql = "SELECT id, last_name, gender, email, dept_id, status FROM emp_tbl where dept_id = '2'";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			stat = conn.createStatement();
			System.out.println("连接=" + conn + "，语句 = " + stat);
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				String lastName = rs.getString(2);
				String gender = rs.getString(3);
				String email = rs.getString(4);
				
				System.out.println("id=" + id + ", lastName=" + lastName + ", gender = " + gender + ", email = " + email);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(stat, conn, rs);
		}
	}
	
	/**
	 * 通用的更新方法：insert, update, delete
	 * 版本1
	 * @param sql
	 */
	public void updateV1(String sql) {
		Connection conn = null;
		Statement stat = null;
		try {
			// 1.获取数据库连接
			conn = JdbcUtil.getConnection();
			// 3.准备删除的sql语句
//			String sql = "delete from emp_tbl where id = '20064'";
			// 4.执行插入
			// 4.1 获取操作sql语句的statement对象； 
//			调用Connection的  createStatement() 方法来获取
			stat = conn.createStatement();
			System.out.println("连接=" + conn + "，语句 = " + stat);
			// 4.2 调用statement对象的 executeUpdate(sql) 执行sql语句进行插入
			int deleteRows = stat.executeUpdate(sql);
			System.out.println("删除记录行数：" + deleteRows);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConn(stat, conn);
		}
	}
	/**
	 * 测试delete 删除语句  
	 */
	@Test
	public void testDeleteStatement() {
		Connection conn = null;
		Statement stat = null;
		try {
			// 1.获取数据库连接
			conn = getConnectionByDriverManager();
			// 3.准备删除的sql语句
			String sql = "delete from emp_tbl where id = '20064'";
			// 4.执行插入
			// 4.1 获取操作sql语句的statement对象； 调用Connection的  createStatement() 方法来获取
			stat = conn.createStatement();
			System.out.println("连接=" + conn + "，语句 = " + stat);
			// 4.2 调用statement对象的 executeUpdate(sql) 执行sql语句进行插入
			int deleteRows = stat.executeUpdate(sql);
			System.out.println("删除记录行数：" + deleteRows);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
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
	
	/**
	 * 向指定的数据表中插入一条记录，
	 *  1、Statement 用于执行sql语句的对象；
	 *  1.1、通过 Connection.createStatement() 方法来获取
	 *  1.2、通过 executeUpdate(sql) 可以执行sql语句
	 *  1.3、sql语可以是 insert， update 或 delete， 但不能是 select
	 *  2、Connection， Statement 都是应用程序和数据库服务器的连接资源；使用后一定要关闭
	 *  （try-catch-finally块中关闭）
	 *  3、关闭顺序：先关闭后获取的，即先关闭 Statement 后关闭 Connection 
	 * @throws Exception
	 */
	@Test
	public void testInsertStatement() {
		Connection conn = null; Statement stat = null;
		try {
			// 1.获取数据库连接
			conn = JdbcUtil.getConnection();
			// 3.准备插入插入的sql语句
			String sql = "INSERT INTO emp_tbl(id, last_name, gender, email, dept_id)"
	                     +"VALUES(null, 'tangrong11', 'm', 'tangrong@qq.com', 1)";
			// 4.执行插入
			// 4.1 获取操作sql语句的statement对象； 调用Connection的  createStatement() 方法来获取
			stat = conn.createStatement();
			System.out.println("连接=" + conn + "，语句 = " + stat);
			// 4.2 调用statement对象的 executeUpdate(sql) 执行sql语句进行插入
			int insertRows = stat.executeUpdate(sql);
			System.out.println("插入记录行数：" + insertRows);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		  JdbcUtil.closeStatAndConn(stat, conn);
		}
	}
	
	@Test
	public void testGetConnectionByDriverManager() throws Exception {
		System.out.println("testGetConnectionByDriverManager conn = " + getConnectionByDriverManager());
	}
	/**
	 * 通过 DriverManager获取 数据库连接 Connection
	 * @throws Exception 
	 */
	public Connection getConnectionByDriverManager() throws Exception {

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
