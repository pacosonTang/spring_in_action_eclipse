package com.swjtu.jdbc.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import com.swjtu.jdbc.utils.JdbcUtil;


public class JdbcTest {
	
	/**
	 * 使用 dbcp 数据库连接池
	 * 如何使用 dbcp数据库连接池 
	 * 1、创建数据库连接池
	 * 2、
	 * @throws Exception 
	 */
	@Test
	public void testDBCP() throws Exception {
		InputStream is = JdbcTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties props = new Properties();
		props.load(is);
		
		BasicDataSource dataSource = null;

		/*创建 dbcp 数据库源实例*/
		dataSource = new BasicDataSource();
		
		/*为数据源实例指定必须的属性*/
		dataSource.setUsername(props.getProperty("user"));
		dataSource.setPassword(props.getProperty("password"));
		dataSource.setUrl(props.getProperty("url"));
		dataSource.setDriverClassName(props.getProperty("driver"));
		
		/*指定数据源的一些可选属性*/
		// 初始化连接数的个数
		dataSource.setInitialSize(10);
		// 指定最大连接数：在数据库连接池中保存的最多的空闲连接的数量
		dataSource.setMaxIdle(50);
		// 指定最小连接数：在数据库连接池中保存的最少的空闲连接的数量
		dataSource.setMinIdle(5);
		// 等待数据库连接池分配连接的最长时间 单位为毫秒，
		// 超过该时间， 将抛出异常
		dataSource.setMaxWaitMillis(1000 *5); // 最长等待时间， 单位毫秒
		
		// BasicDataSourceFactory 数据源工厂： 29分钟的时间处 
		
		/*从数据库源中获取数据库连接*/
		Connection conn = dataSource.getConnection();
		System.out.println(conn.getClass());
	}
	
	
	/**
	 * 向数据库表 user_tbl 中插入10万条记录: 采用 预编译语句 PreparedStatement和 jdbc批量操作实现批量
	 * 如何插入才能用时最短。
	 */
	@Test
	public void testBatch() {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			String sql = "insert into user_tbl(user_name, password, balance) values(?, ?, ?)";
			conn = JdbcUtil.getConnection();
			JdbcUtil.beginTx(conn);// 开启事务
			// 创建sql语句对象
			stat = conn.prepareStatement(sql);
			long begin = System.currentTimeMillis();
			for (int i=1; i<=100000; i++) {
				stat.setObject(1, "u_zhoujielun" + i);
				stat.setObject(2, "p_zhoujielun" + i);
				stat.setObject(3, 1000+i);
				// 积攒sql
				stat.addBatch();
				// 当积攒到一定程度，就统一地执行一次，并且清空先前积攒的sql。
				if (i % 300 == 0) {
					stat.executeBatch(); // 执行批量
					stat.clearBatch(); // 清空批量
				}
			}
			// for 循环后还需要执行和清空批量 
			if (100000 % 300 != 0) {
				stat.executeBatch();
				stat.clearBatch();
			}
			long end = System.currentTimeMillis();
			System.out.println("用时 = " + (end - begin) + "毫秒"); // 17266毫秒
			JdbcUtil.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(stat, conn, null);
		}
	}
	
	/**
	 * 向数据库表 user_tbl 中插入10万条记录: 采用 预编译语句 PreparedStatement和for循环实现批量
	 * 如何插入才能用时最短。
	 */
	@Test
	public void testBatchWithPreparedStatementForLoop() {
		Connection conn = null;
		PreparedStatement stat = null;
		
		try {
			String sql = "insert into user_tbl(user_name, password, balance) values(?, ?, ?)";
			conn = JdbcUtil.getConnection();
			JdbcUtil.beginTx(conn);// 开启事务
			// 创建sql语句对象
			stat = conn.prepareStatement(sql);
			long begin = System.currentTimeMillis();
			for (int i=0; i<100000; i++) {
				stat.setObject(1, "u_zhoujielun" + i);
				stat.setObject(2, "p_zhoujielun" + i);
				stat.setObject(3, 1000+i);
				// 插入数据
				stat.executeUpdate(); 
			}
			long end = System.currentTimeMillis();
			System.out.println("用时 = " + (end - begin) + "毫秒"); // 16704毫秒
			JdbcUtil.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(stat, conn, null);
		}
	}
	
	/**
	 * 向数据库表 user_tbl 中插入10万条记录: 采用 预编译语句 Statement和for循环实现批量
	 * 如何插入才能用时最短。
	 */
	@Test
	public void testBatchWithStatementForLoop() {
		Connection conn = null;
		Statement stat = null;
		
		try {
			conn = JdbcUtil.getConnection();
			JdbcUtil.beginTx(conn);// 开启事务
			stat = conn.createStatement();
			// 创建sql语句对象
			long begin = System.currentTimeMillis();
			for (int i = 1; i <= 100000; i++) {
				String sql = "insert into user_tbl(user_name, password, balance) "
						+ "values('u_lisi_" + i +"', 'p_lisi_" + i + "', " + 1000+i + ")";
				// 插入数据
				stat.executeUpdate(sql);
			} 
			long end = System.currentTimeMillis();
			System.out.println("用时 = " + (end - begin) + "毫秒"); // 15771毫秒
			JdbcUtil.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(stat, conn, null);
		}
	}
	
}
