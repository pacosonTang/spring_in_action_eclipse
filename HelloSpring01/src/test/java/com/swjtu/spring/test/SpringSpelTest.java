package com.swjtu.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swjtu.spring.beans.spel.Address;
import com.swjtu.spring.beans.spel.Car;
import com.swjtu.spring.beans.spel.Person;

public class SpringSpelTest {
	
	/**
	 * SpEL(spring 表达式语言)的测试案例
	 */
	@Test
	public void spelTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-spel.xml");
		/*使用 spel 为属性赋值一个字面值, 获取该bean*/
		Address address = (Address)ctx.getBean("address");
		System.out.println(address);
		
		/*使用 spel 引用类的静态属性  并获取该bean*/
		Car car = (Car)ctx.getBean("car");
		System.out.println(car);
		
		/*使用 spel 来引用其他的bean的属性 并获取该bean*/
		Person person = (Person)ctx.getBean("person");
		System.out.println(person);
	}
} 
