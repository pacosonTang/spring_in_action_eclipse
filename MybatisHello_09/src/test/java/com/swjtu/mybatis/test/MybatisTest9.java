package com.swjtu.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.swjtu.mybatis.bean.Employee;
import com.swjtu.mybatis.dao.EmployeeMapper;

public class MybatisTest9 {
	

	/**
	 * 通过 employee id 查询 emp
	 * @throws IOException
	 */
	@Test
	public void test2() throws IOException {
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			mapper.getEmployeeId(1);
			Employee emp = mapper.getEmployeeId(1);
			System.out.println(emp);
		} finally { 
			session.close();
		}
	}
	
	/**
	 * 1、获取 sqlSessionFactory 对象；
	 * 2、获取 SqlSession 对象；
	 * 3、获取 接口的代理对象（MapperProxy）
	 * 4、执行增删改查 方法
	 * 5、
	 * @throws IOException
	 */ 
	@Test
	public void test1() throws IOException {
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Employee emp = (Employee) session.selectOne("com.swjtu.mybatis.dao.EmployeeMapper.getEmployeeId", "1");
			System.out.println(emp);
		} finally { 
			session.close();
		}
	}
	/**
	 * 获取SqlSessionFactory 
	 * @return
	 */
	public SqlSessionFactory getSqlSessionFactory() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try { 
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			return sqlSessionFactory;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
