package com.swjtu.spring.aop.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swjtu.spring.aop.xml.ArithmeticCalculator;

public class SpringAopByXmlTest {
	
	/* 测试 spring aop 通过xml配置的方式 
	*/ 
	@Test
	public void springAopWithMultipleAspectsTest() {
		// 创建 spring ioc 容器
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-xml.xml");
		
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
