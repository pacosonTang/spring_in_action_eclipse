package com.swjtu.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swjtu.spring.beans.autowire.Person;

public class SpringAutowireTest {
	
	/**
	 * 自动装配的测试案例
	 */
	@Test
	public void autowireTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		
		Person person = (Person)ctx.getBean("person");
		System.out.println(person);
	}
}
