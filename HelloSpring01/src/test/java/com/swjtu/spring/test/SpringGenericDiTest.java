package com.swjtu.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swjtu.spring.beans.generic.di.UserService;

public class SpringGenericDiTest {

	/**
	 * spring 泛型依赖注入的测试用例 
	 */
	@Test
	public void springAnnotationWithRelationTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-generic-di.xml");
		
		UserService userService = (UserService) ctx.getBean("userService");
		userService.add();
	}
}






