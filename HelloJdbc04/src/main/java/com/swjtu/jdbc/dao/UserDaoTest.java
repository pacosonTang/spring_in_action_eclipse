package com.swjtu.jdbc.dao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.swjtu.jdbc.bean.User;
import com.swjtu.jdbc.utils.JdbcUtil;

public class UserDaoTest {
	
	UserDao<User> userDao = new UserDao<>();
	
	@Test
	public void testBatch() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetForValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryList() {
		fail("Not yet implemented");
	}

	@Test
	public void testQuery() {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "select user_name as userName, password as password "
					+ "from user_tbl where rcrd_id = ?";
			User user = userDao.query(conn, sql, "500000");
			System.out.println(user);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(null, conn, null);
		}
	}
	
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test1() {
		this.getClass().getGenericSuperclass();
	}
}
