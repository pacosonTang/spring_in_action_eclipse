package com.swjtu.spring.aop.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;

import com.swjtu.spring.aop.ArithmeticCalculator;

public class SpringAopTest {
	
	/* 测试 spring aop 切面优先级的问题，
		配置了多个切面： 哪个切面先执行，而哪个切面后执行呢？
		使用 @Order(1) 设置切面的优先级来解决，优先级值越小，优先级越高。
	*/ 
	@Test
	public void springAopWithMultipleAspectsTest() {
		// 创建 spring ioc 容器
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// 从ioc容器中获取 bean 实例
		ArithmeticCalculator calculator = ctx.getBean(ArithmeticCalculator.class);
		System.out.println("calculator.getClass().getName() = " + calculator.getClass().getName());
		int result = 0;
		
		// 使用bean
		/*result = calculator.add(3, 6); // 加法 
		System.out.println("result = " + result);*/
		
		result = calculator.div(1000, 1); // 除法 
		System.out.println("result = " + result);
	}
	
	
	// 测试 面向切面 spring aop 编程，包括结果通知@AfterReturning
	// ，异常通知 @AfterThrowing和环绕通知 @Around 
	@Test
	public void springAopTest() {
		// 创建 spring ioc 容器
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// 从ioc容器中获取 bean 实例
		ArithmeticCalculator calculator = ctx.getBean(ArithmeticCalculator.class);
		System.out.println("calculator.getClass().getName() = " + calculator.getClass().getName());
		int result = 0;
		
		// 使用bean
		/*result = calculator.add(3, 6); // 加法 
		System.out.println("result = " + result);*/
		
		result = calculator.div(1000, 1); // 除法 
		System.out.println("result = " + result);
		
	}
	
}
