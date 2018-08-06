package com.swjtu.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.junit.Test;

import com.swjtu.jdbc.utils.JdbcUtil;


public class JdbcTest {
	
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
