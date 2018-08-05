package com.swjtu.jdbc.test;

import java.util.List;

import org.junit.Test;

import com.swjtu.jdbc.bean.User;
import com.swjtu.jdbc.dao.DAOUtil;

// dao 层的测试用例
public class DaoTest {
	DAOUtil dao = new DAOUtil();
	
	@Test
	public void testGetForValueWithSingleField() {
		/*查询某记录的某个字段值*/
		String sql = "select user_name as userName, password as password "
				+ "from user_tbl where rcrd_id = ?";
		Object obj = dao.getForValueWithSingleField(sql, "2");
		System.out.println(obj);
		
		/*统计记录条数*/
		sql = "select count(1) from user_tbl where rcrd_id < ?";
		obj = dao.getForValueWithSingleField(sql, "1000");
		System.out.println(obj);
		
	}
	
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
		
		for (int i = 1000; i < 10000; i++) {
			dao.update(sql, "user" + i, "pwd" + i);
		}
	}
}
