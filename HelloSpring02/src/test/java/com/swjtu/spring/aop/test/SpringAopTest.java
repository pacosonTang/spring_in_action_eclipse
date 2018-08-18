package com.swjtu.spring.aop.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swjtu.spring.aop.helloworld.ArithmeticCalculator;
import com.swjtu.spring.aop.helloworld.ArithmeticCalculatorLoggingProxy;
import com.swjtu.spring.aop.helloworld.ArithmeticCalulatorImpl;

public class SpringAopTest {
	
	// 测试 面向切面 spring aop 编程 
	@Test
	public void springAopTest2() {
		// 创建 spring ioc 容器
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// 从ioc容器中获取 bean 实例
		com.swjtu.spring.aop.impl.ArithmeticCalculator calculator = 
				ctx.getBean(com.swjtu.spring.aop.impl.ArithmeticCalculator.class);
		
		// 使用bean
		int result = calculator.add(3, 6);
		System.out.println("result = " + result);
	}
	
	// 测试 基于动态代理的整数加减乘除 
	@Test
	public void springAopWithDynamicProxyTest() {
		ArithmeticCalculator calculator = new ArithmeticCalulatorImpl();
		// 获得代理对象 
		ArithmeticCalculator proxy = new ArithmeticCalculatorLoggingProxy(calculator).getCalculatorLogProxy();
		
		int result = proxy.add(1, 2);
		System.out.println("calculator.add(1, 2) ==> " + result);
		
		result = proxy.div(4, 2);
		System.out.println("calculator.div(4, 2) ==> " + result);
	}
	
	// 测试 整数的加减乘除 
	@Test
	public void springAopTest() {
		ArithmeticCalculator calculator = null;
		
		calculator = new ArithmeticCalulatorImpl();
		
		int result = calculator.add(1, 2);
		System.out.println("calculator.add(1, 2) ==> " + result);
		
		result = calculator.div(4, 2);
		System.out.println("calculator.div(4, 2) ==> " + result);
	}
}
