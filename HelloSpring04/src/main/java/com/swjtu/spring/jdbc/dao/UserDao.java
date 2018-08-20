package com.swjtu.spring.jdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.swjtu.spring.jdbc.bean.User;

/**
 * 在 dao层使用 jdbcTemplate 进行 sql 操作
 */
@Repository
public class UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 根据id 查询 User对象 
	 * @param id
	 * @return
	 */
	public User get(Integer id) {
		String sql = "select user_name AS userName"
			     + ", password AS password from user_tbl where rcrd_id = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return user;
	}
}
