package com.swjtu.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swjtu.spring.beans.autowire.Car;

public class SpringScopeTest {
	
	/**
	 * 装配bean的作用域的测试案例
	 */
	@Test
	public void autowireTest() {
		// 当scope设置为 singleton时， 当家加载 beans-scope.xml 时， 
		// spring会调用 Car的默认构造器实例化一个单例的Car对象 
		// 当scope设置为 prototype原型时，不会去创建。
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-scope.xml");
		
		// 这里创建bean使用的是 Car的 无参构造器
		Car car = (Car)ctx.getBean("car");
		System.out.println(car);
		
		Car car2 = (Car)ctx.getBean("car");
		System.out.println(car2);
		
		System.out.println("car == car2 ? " + (car == car2));
	}
}
