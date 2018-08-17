package com.swjtu.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swjtu.spring.beans.annotation.TestObject;
import com.swjtu.spring.beans.annotation.controller.UserController;
import com.swjtu.spring.beans.annotation.repository.UserRepository;
import com.swjtu.spring.beans.annotation.service.UserService;

public class SpringAnnotationTest {

	/**
	 * 使用spring注解创建bean并构建bean之间的关联关系的测试用例
	 */
	@Test
	public void springAnnotationWithRelationTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-annotation.xml");
		
		// 获取Controller注解修饰的类对象
		UserController controller = (UserController) ctx.getBean("userController");
		System.out.println(controller);
		// controller.execute 调用了  userService.add() 方法
		// userService.add() 调用了 userRepository.save() 方法
		controller.execute();  
		System.out.println("springAnnotationWithRelationTest Junit4 test method.");
	}
	
	/**
	 * 使用spring注解创建bean的测试用例
	 */
//	@Test
	public void springAnnotationTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-annotation.xml");
		
		// 获取Component 注解修饰的类对象
		TestObject testObject = (TestObject) ctx.getBean("testObject");
		System.out.println(testObject);
		
		// 获取Controller注解修饰的类对象
		UserController controller = (UserController) ctx.getBean("userController");
		System.out.println(controller);
		
		// 获取Repository注解修饰的类对象
		UserRepository userRepository = (UserRepository) ctx.getBean("userRepositoryImpl");
		System.out.println(userRepository);
		
		// 获取 Service 注解修饰的类对象
		UserService userService = (UserService) ctx.getBean("userService");
		System.out.println(userService);
	}
}
