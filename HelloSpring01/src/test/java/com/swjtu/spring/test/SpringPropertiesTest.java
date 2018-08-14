package com.swjtu.spring.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringPropertiesTest {
	
	/**
	 * 使用外部属性文件装配bean的测试用例
	 * @throws Exception 
	 */
	@Test
	public void springPropertiesTest() throws Exception {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("beans-properties.xml");
		
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		// 获取数据源即数据库连接池中的连接
		System.out.println(dataSource.getConnection());
	}
}
