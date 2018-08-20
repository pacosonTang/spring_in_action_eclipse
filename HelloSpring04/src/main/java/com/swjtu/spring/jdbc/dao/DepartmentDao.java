package com.swjtu.spring.jdbc.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.swjtu.spring.jdbc.bean.Department;
import com.swjtu.spring.jdbc.bean.User;

/**
 * JdbcDaoSupport 的测试用例（不推荐使用 JdbcDaoSupport ，这里仅仅是了解而已）
 */
@Repository
public class DepartmentDao extends JdbcDaoSupport{
	
	/**
	 * 使用 JdbcDaoSupport ，必须要注入一个 DataSource 或者
	 * JdbcTemplate 对象 
	 * @param dataSource
	 */
	@Autowired
	private void setDataSource2(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	public Department getDept(String id) {
		String sql = "select id as id"
			     + ", dept_name AS deptName from dept_tbl where id = ?";
		RowMapper<Department> rowMapper = new BeanPropertyRowMapper<>(Department.class);
		Department dept = this.getJdbcTemplate().queryForObject(sql, rowMapper, id);
		return dept;
	}
	
	/*public User get(Integer id) {
		String sql = "select user_name AS userName"
			     + ", password AS password from user_tbl where rcrd_id = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return user;
	}*/
}
