package com.swjtu.jdbc.test;

import java.util.List;

import org.junit.Test;

import com.swjtu.jdbc.bean.User;
import com.swjtu.jdbc.dao.DAOUtil;

public class DaoTest {
	
	DAOUtil dao = new DAOUtil();
	
	/**
	 * 测试查询单个user的功能
	 */
	@Test
	public void testListQry() {
		String sql = " select user_name as userName, password as password"
				+ " from  user_tbl where rcrd_id < ?";
		
		List<User> list = dao.getList(User.class, sql, "10");
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	
	
	/**
	 * 测试查询单个user的功能
	 */
	@Test
	public void testSingleQry() {
		String sql = " select user_name as userName, password as password"
				+ " from  user_tbl where rcrd_id = ?";
		
		User user = dao.getSingle(User.class, sql, "3");
		System.out.println(user);
	}
	
	/**
	 * 测试插入用户的功能
	 */
	@Test
	public void testUpdate() {
		String sql = "insert into user_tbl (user_name, password) values(?, ?)";
		
		dao.update(sql, "user3333", "pwd3333");
	}
}
