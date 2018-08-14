package com.swjtu.spring.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swjtu.spring.beans.cycle.Car;

public class SpringCycleTest {
	
	/**
	 * spring装配bean的生命周期的测试案例
	 */
	@Test
	public void springCycleTest() {
		ClassPathXmlApplicationContext ctx = 
				new ClassPathXmlApplicationContext("beans-cycle.xml");
		
		Car car = (Car)ctx.getBean("car");
		System.out.println(car);
		// 关闭ioc容器
		ctx.close();
	}
}
