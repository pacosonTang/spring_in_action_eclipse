package com.swjtu.spring.jdbc.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.swjtu.spring.jdbc.bean.Department;
import com.swjtu.spring.jdbc.bean.User;
import com.swjtu.spring.jdbc.dao.DepartmentDao;
import com.swjtu.spring.jdbc.dao.UserDao;

/**
 * spring jdbcTemplate 的测试用例
 * @author pacoson
 *
 */
public class JDBCTest {
	
	private ApplicationContext ctx;
	private JdbcTemplate jdbcTemplate;
	private UserDao userDao;
	private DepartmentDao departmentDao;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = ctx.getBean(JdbcTemplate.class);
		userDao = ctx.getBean(UserDao.class);  
		departmentDao = ctx.getBean(DepartmentDao.class);
		namedParameterJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);
	}
	
	/**
	 * 基于 NamedParameterJdbcTemplate 和 SqlParameterSource 插入数据
	 * 1、使用具名参数 NamedParameterJdbcTemplate 时，可以使用  
	 * update(String sql, SqlParameterSource paramSource) 进行更新操作；
	 * 2、sql 语句中的 参数名 和 类的属性名一致；
	 * 3、使用 SqlParameterSource 的实现类 BeanPropertySqlParameterSource 作为参数
	 */
	@Test
	public void testNamedParameterJdbcTemplateBySqlParameterSource() {
		String sql = "insert into user_tbl(user_name, password) values(:userName, :password)";
		// 创建对象
		User user = new User(); 
		user.setUserName("tianqi");
		user.setPassword("999999");
		
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
		// 基于 NamedParameterJdbcTemplate 插入数据
		int updRows = namedParameterJdbcTemplate.update(sql, sqlParameterSource);
		System.out.println("updRows = " + updRows);
	}
	/**
	 * 基于 NamedParameterJdbcTemplate 插入数据: 可以为参数起名字；
	 * 1、好处： 若有多个参数，则不用再去对应位置，直接对应参数名，便于维护；
	 * 2、缺点：较为麻烦
	 */
	@Test
	public void testNamedParameterJdbcTemplate() {
		String sql = "insert into user_tbl(user_name, password) values(:user_name, :password)";
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("user_name", "tangxiao");
		paramMap.put("password", "123456");
		// 基于 NamedParameterJdbcTemplate 插入数据
		int updRows = namedParameterJdbcTemplate.update(sql, paramMap);
		System.out.println("updRows = " + updRows);
	}
	
	/**
	 * 测试基于 JdbcDaoSupport 的 DepartmentDao
	 */
	@Test
	public void testDepartmentDaoByJdbcDaoSupport() {
		Department dept = departmentDao.getDept("2");
		System.out.println(dept);
	}
	
	/**
	 * 测试基于 jdbcTemplate 的 UserDao 
	 */
	@Test
	public void testUserDao() {
		User user = userDao.get(500001);
		System.out.println(user);
	}
	
	/**
	 * 获取单个列的中，或作统计查询；
	 * 使用的方法为 queryForObject(String sql, Class<T> requiredType, Object... args)
	 */
	@Test
	public void testQueryForObject2() {
		String sql = "select count(1) from user_tbl where rcrd_id > ?";
		long count = jdbcTemplate.queryForObject(sql, Long.class, 1);
		System.out.println("count = " + count);
	}
	
	/**
	 * 查询一组对象，即实体类的集合；
	 * 注意调用的不是 queryForList 方法， 而是 query 方法
	 */
	@Test
	public void testQueryForList() {
		String sql = "select user_name AS userName"
			     + ", password AS password "
			     + " from user_tbl where rcrd_id > ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		List<User> list = jdbcTemplate.query(sql, rowMapper, "500001");
		
		System.out.println(list);
	}
	
	/**
	 * 从数据库中获取一条记录， 实际得到对应的一个对象，
	 * 为级联属性赋值, 给 user 的 department属性的 id属性赋值。
	 * 不支持级联属性赋值；
	 */
	@Test
	public void testQueryForObjectAndAssignCascadeProperty() {
		String sql = "select user_name AS userName"
				     + ", password AS password"
				     + ", dept_id as \"department.id\" from user_tbl where rcrd_id = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper, "500001");
		System.out.println(user);
	}
	
	/**
	 * 从数据库中获取一条记录， 实际得到对应的一个对象，
	 * 而需要调用 public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args) 
	 * 1、RowMapper 指定如何去映射结果集的行， 常用的实现类为 BeanPropertyRowMapper；
	 * 2、使用 sql 中列的别名完成列名和 类属性名的映射，如 user_name AS userName；
	 * 3、不支持级联属性赋值；JdbcTemplate 到底是一个 jdbc的小工具，而不是 ORM 框架；
	 */
	@Test
	public void testQueryForObject() {
		String sql = "select user_name AS userName"
				     + ", password AS password from user_tbl where rcrd_id = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper, "500001");
		System.out.println(user);
	}

	/**
	 * 执行批量更新-批量的insert， update 和 delete 
	 * 最后一个参数是 Object[] 的List类型： 因为修改一条记录需要一个 Object的数组
	 * ， 那么多条就需要多个 Object的数组，即 List<Object[]> 类型。
	 */
	@Test
	public void testBatchUpdate() {
		String sql = "insert into user_tbl(user_name, password) values(?, ?)";
		List<Object[]> batchArgs = new ArrayList<>();
		batchArgs.add(new Object[]{"zhangsan", "zhangsan"});
		batchArgs.add(new Object[]{"lisi", "lisi"});
		batchArgs.add(new Object[]{"wangwu", "wangwu"}); 
		
		int[] updRows = jdbcTemplate.batchUpdate(sql, batchArgs);
		for (int rows : updRows) {
			System.out.println("rows = " + rows);
		}
	}
	/**
	 * 执行 insert， update， delete 
	 */
	@Test
	public void testUpdate() {
		String sql = "update user_tbl set user_name = ? where rcrd_id = ?";
		int updRows = jdbcTemplate.update(sql, "tangrong", "1");
		System.out.println("updRows = " + updRows);
	}
	/**
	 * 测试获取数据源
	 */
	@Test
	public void testDataSource() {
		DataSource dataSource = ctx.getBean(DataSource.class);
		try {
			System.out.println(dataSource.getConnection());
			System.out.println(jdbcTemplate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
