package com.swjtu.jdbc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 操作jdbc的工具类-封装了一些工具方法
 * @author pacoson
 */
public class JdbcUtil {
	
	
	/**
	 * 开启事务
	 * @param conn
	 */
	public static void beginTx(Connection conn) {
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static DataSource dataSource ;
	
	// 数据库连接池应该只被初始化一次
	static {
		dataSource = new ComboPooledDataSource("helloc3p0");
	}
	
	
	
	/**
	 * 回滚事务
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 提交事务
	 * @param conn
	 */
	public static void commit(Connection conn) {
		if (conn != null) {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
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
			conn = JdbcUtil.getConnection();
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
			JdbcUtil.closeStatAndConn(stat, conn);
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
			conn = JdbcUtil.getConnection();
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
			JdbcUtil.closeStatAndConn(stat, conn);
		}
	}
	
	/**
	 * 获取连接的方法。
	 * 通过读取配置文件从数据库服务器获取一个连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		return dataSource.getConnection();
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
				// 数据库连接池的Connection对象 进行close时：
				// 并不是真的进行关闭，而是把该数据库连接归还到 数据库连接池中；
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
