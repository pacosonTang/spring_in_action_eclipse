package com.swjtu.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.junit.Test;

import com.swjtu.jdbc.dao.DAOUtil;
import com.swjtu.jdbc.utils.JdbcUtil;

public class TransactionTest {
	
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
				System.out.println("事务回滚。");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} 
		
		/*String sql = "update user_tbl set balance = balance - 500 where user_name = ?";
		int updRows = dao.update(sql, "zhangsan");
		System.out.println("更新行数 = " + updRows);
		
		这里会抛异常 异常抛出后 数据如何恢复到以前的状态
		int i = 10/0;
		System.out.println(i);
		
		sql = "update user_tbl set balance = ? where user_name = ?";
		updRows = dao.update(sql, "1000", "zhangsan");
		System.out.println("更新行数 = " + updRows);*/
	}
	
	public int update(Connection conn, String sql, Object... args) {
		PreparedStatement stat = null;
		
		try {
			conn = JdbcUtil.getConnection();
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
			JdbcUtil.closeStatAndConnAndResultSet(stat, conn, null);
		}
		return 0;
	}
}
