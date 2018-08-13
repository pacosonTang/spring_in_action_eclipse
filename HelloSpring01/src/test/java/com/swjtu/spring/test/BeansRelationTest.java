package com.swjtu.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swjtu.spring.beans.autowire.Address;
import com.swjtu.spring.beans.autowire.Person;

public class BeansRelationTest {
	
	/**
	 * bean 之间的关系：配置bean上的依赖关系的测试用例
	 */
	@Test
	public void dependencyTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-relation.xml");
		
		Person person = (Person) ctx.getBean("person");
		System.out.println(person);
	}
	
	/**
	 * bean 之间的关系：配置上的继承的测试用例
	 */
	@Test
	public void autowireTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-relation.xml");
		
		Address address = (Address) ctx.getBean("address");
		System.out.println(address);
		
		address = (Address) ctx.getBean("address2");
		System.out.println(address);
		
		address = (Address) ctx.getBean("address4");
		System.out.println(address);
	}
}
