package com.swjtu.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.swjtu.jdbc.bean.User;
import com.swjtu.jdbc.dao.DAOUtil;
import com.swjtu.jdbc.utils.JdbcUtil;

public class TransactionTest {
	DAOUtil dao = new DAOUtil();
	
	
	/**
	 * 测试事务的隔离级别
	 * 在 jdbc程序中，通过 Connection.setTransactionIsolution()
	 * 设置事务隔离级别
	 */
	@Test
	public void testTransactionIsolationRead() {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection(); // 获取数据库连接
			conn.setAutoCommit(false);
			
			/* 更新操作 */
			String sql = "select balance as balance from user_tbl where user_name = ? ";
			User user = dao.getSingle(User.class, sql, "zhangsan");

			// 如果没有抛出异常，则提交事务
			conn.commit();
			System.out.println("提交成功！！");
			System.out.println(user);
		} catch (Exception e) {
			try {
				// 回滚事务
				conn.rollback();
				System.out.println("事务回滚。");
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				JdbcUtil.closeStatAndConnAndResultSet(null, conn, null);
			}
		} 
	}
	
	/**
	 * 测试事务的隔离级别
	 * 在 jdbc程序中，通过 Connection.setTransactionIsolution()
	 * 设置事务隔离级别
	 */
	@Test
	public void testTransactionIsolationUpdate() {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection(); // 获取数据库连接
			// 设置数据库事务隔离级别读未提交
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED); 
			
			/* 更新操作 */
			String sql = "update user_tbl set balance = balance - 500 where user_name = ?";
			int updRows = this.update(conn, sql, "zhangsan");
			System.out.println("更新记录行数 = " + updRows);
			
			// 如果没有抛出异常，则提交事务
			conn.commit();
			System.out.println("提交成功！！");
		} catch (Exception e) {
			try {
				// 回滚事务
				System.out.println("事务回滚。");
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				JdbcUtil.closeStatAndConnAndResultSet(null, conn, null);
			}
		} 
	}
	
	/**
	 * 张三给李四 汇款500元。
	 * jdbc事务：事务回滚 。
	 * 
	 * 关于事务：
	 * 1、如果多个操作，每个操作使用的是自己的单独的连接，则无法保证事务；
	 * 2、具体步骤：
	 * 1）事务开始前，开始事务：即取消 connection的默认提交行为， conn.setAutoCommit(false);
	 * 2）如果事务操作都成功，则提交事务： conn.commit()
	 * 3）回滚事务：若出现异常，则在 catch 块中回滚事务；
	 */
	@Test
	public void testTransaction() {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection(); // 获取数据库连接
			conn.setAutoCommit(false); // 开始事务 取消自动提交
			// 设置mysql数据库的事务隔离级别为可重复读
			conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ); 
			String sql = "update user_tbl set balance = balance - 500 where user_name = ?";
			int updRows = this.update(conn, sql, "zhangsan");
			System.out.println("更新记录行数 = " + updRows);
			
			/*这里会抛异常 异常抛出后 数据如何恢复到以前的状态*/
			int i = 10 / 0;
			
			sql = "update user_tbl set balance = balance - 500 where user_name = ?";
			updRows = this.update(conn, sql, "zhangsan");
			System.out.println("第二次更新记录行数 = " + updRows);
			
			// 如果没有抛出异常，则提交事务
			conn.commit();
			System.out.println("提交成功！！");
		} catch (Exception e) {
			try {
				// 回滚事务
				conn.rollback();
				System.out.println("事务回滚成功。");
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				JdbcUtil.closeStatAndConnAndResultSet(null, conn, null);
			}
		} 
	}

	/**
	 * 执行update更新操作
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 */
	public int update(Connection conn, String sql, Object... args) {
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql);
			int i = 1;
			for (Object arg : args) {
				stat.setObject(i++, arg);
			}
			int updRows = stat.executeUpdate();
			return updRows;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 这里不要关闭数据库连接 connection
		}
		return 0;
	}
}









