package com.swjtu.spring.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swjtu.spring.beans.factorybean.Car;

public class SpringFactoryBeanTest {
	
	/**
	 * spring 通过FactoryBean装配bean 的测试案例
	 */
	@Test
	public void springFactoryTest() {
		ClassPathXmlApplicationContext ctx = 
				new ClassPathXmlApplicationContext("beans-factorybean.xml");
		/**/
		Car car = (Car)ctx.getBean("car");
		System.out.println(car);
		
		// 关闭ioc容器
		ctx.close();
	}
}
