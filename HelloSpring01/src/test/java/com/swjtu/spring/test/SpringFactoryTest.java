package com.swjtu.spring.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swjtu.spring.beans.factory.Car;

public class SpringFactoryTest {
	
	/**
	 * 通过静态工厂方法装配bean的测试案例
	 */
	@Test
	public void springFactoryTest() {
		ClassPathXmlApplicationContext ctx = 
				new ClassPathXmlApplicationContext("beans-factory.xml");
		/*通过静态工厂方法来配置bean*/
		Car car = (Car)ctx.getBean("car1");
		System.out.println(car);
		
		/*通过实例工厂方法创建实例bean  */
		car = (Car)ctx.getBean("car2");
		System.out.println(car);
		
		// 关闭ioc容器
		ctx.close();
	}
}
