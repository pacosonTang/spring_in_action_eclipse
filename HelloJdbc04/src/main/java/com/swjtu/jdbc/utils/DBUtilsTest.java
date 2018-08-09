package com.swjtu.jdbc.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.swjtu.jdbc.bean.User;

/**
 * 测试 DBUtil 工具类 
 * @author pacoson
 *
 */
public class DBUtilsTest {
	
	/**
	 * 使用 ScalarHandler 把结果集转为一个数值返回 ，
	 * 这个数值可以是任意基本数据类型,如字符串或 Date等 
	 */
	@Test
	public void testScalarHandler() {
		Connection conn = null; 
		String sql = "select user_name as USERNAME, password as PASSWORD from user_tbl where rcrd_id > ?";
		
		try {
			conn = JdbcUtil.getConnection();
			// 查询出多条记录 
			Object obj = queryRunner.query(conn, sql
					, new ScalarHandler<>()
					, "499900");
			System.out.println(obj); // u_zhoujielun99901
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(null, conn, null);
		}
	}
	
	
	/**
	 * 使用 MapListHandler 把结果集转为 List<Map> 对象, 查询多条记录。
	 * 键： SQL查询的别名
	 * 值：别名的值 
	 */
	@Test
	public void testMapListHandler() {
		Connection conn = null; 
		String sql = "select user_name as USERNAME, password as PASSWORD from user_tbl where rcrd_id > ?";
		
		try {
			conn = JdbcUtil.getConnection();
			// 查询出多条记录 
			List<Map<String, Object>> mapList = queryRunner.query(conn, sql
					, new MapListHandler()
					, "499900");
			for (Map<String, Object> map : mapList) {
				System.out.println(map); // 返回多条记录 
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(null, conn, null);
		}
	}
	
	/**
	 * 使用 MapHandler 把结果集转为一个 Map对象, 只能查询单条记录。
	 * 键： SQL查询的别名
	 * 值：别名的值 
	 */
	@Test
	public void testMapHandler() {
		Connection conn = null; 
		String sql = "select user_name as USERNAME, password as PASSWORD from user_tbl where rcrd_id > ?";
		
		try {
			conn = JdbcUtil.getConnection();
			// 这里只能查询出单条记录 
			Map<String, Object> map = queryRunner.query(conn, sql
					, new MapHandler()
					, "499900");
			System.out.println(map); // 只返回一条记录：{userName=u_zhoujielun99901, password=p_zhoujielun99901}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(null, conn, null);
		}
	}
	
	/**
	 * 使用 BeanListHandler 把结果集转为一个 list， 
	 * 该list 不为null， 但可能为空集合 ，即其size() 有可能返回 0 。
	 * 即 BeanListHandler 将查询结果 存放到 Class对应的对象中。
	 */
	@Test
	public void testBeanListHandler() {
		Connection conn = null; 
		String sql = "select user_name as userName, password as password from user_tbl where rcrd_id > ?";
		
		try {
			conn = JdbcUtil.getConnection();
			// 这里只能查询出单条记录 
			List<User> list = queryRunner.query(conn, sql
					, new BeanListHandler<User>(User.class)
					, "499900");
			for (User user : list) {
				System.out.println(user);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(null, conn, null);
		}
	}
	
	/**
	 * 使用 BeanHandler 查询数据库，单条记录；
	 * 即 BeanHandler 把结果集的第一条记录转为
	 * 创建 BeanHandler 对象时传入的 Class参数对应的对象；
	 */
	@Test
	public void testBeanHandler() {
		Connection conn = null;
		String sql = "select user_name as userName, password as password from user_tbl where rcrd_id > ?";
		
		try {
			conn = JdbcUtil.getConnection();
			// 这里只能查询出单条记录 
			User user = queryRunner.query(conn, sql
					, new BeanHandler<User>(User.class)
					, "499900");
			System.out.println(user);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(null, conn, null);
		}
	}
	
	// 使用 QueryRunner 查询数据库：需要提供  ResultSetHandler 的实现类
	QueryRunner queryRunner = new QueryRunner();
	class MyResultSetHandler implements ResultSetHandler<Object> {
		@Override
		public Object handle(ResultSet rs) throws SQLException {
			List<User> list = new ArrayList<>();
			
			while (rs.next()) {
				String userName = rs.getString(1);
				String password = rs.getString(2);
				User user = new User(userName, password);
				list.add(user);
			}
			return list;
		}
	}
	/**
	 * 使用 QueryRunner 查询数据库： * QueryRunner.query方法的返回值 取决于 ResultSetHandler实现类
	 * 中handle方法的返回值，其中 handle方法有参数 ResultSet ，
	 * 可以利用 ResultSet 整理结果集；
	 */
	@Test 
	public void testQuery() {
		Connection conn = null;
		String sql = "select user_name as userName, password as password from user_tbl where rcrd_id > ?";
		
		try {
			conn = JdbcUtil.getConnection();
			List<User> list = (List<User>) queryRunner.query(conn, sql, new MyResultSetHandler(), "499900");
			for (User user :list) {
				System.out.println(user);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(null, conn, null);
		}
	}
	
	/**
	 * 创建 QueryRunner 类的 update 方法：
	 * 该方法可用于 insert， update 和 delete。
	 */
	@Test
	public void testQueryRunnerUpdate() {
		// 创建 QueryRunner 的实现类 
		QueryRunner queryRunner = new QueryRunner();
		// 使用其 update 方法
		String sql = "update user_tbl set balance = 500 where rcrd_id = ?";
		Connection conn = null;
		
		try {
			conn = JdbcUtil.getConnection();
			// 增删改的sql逻辑 都通过 queryRunner.update 来实现。
			int updRows = queryRunner.update(conn, sql, "500000");
			System.out.println("更新的记录行数 = " + updRows);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(null, conn, null);
		}
		
	}
}
