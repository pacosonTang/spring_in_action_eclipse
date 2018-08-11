package com.swjtu.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swjtu.spring.beans.Car;
import com.swjtu.spring.beans.HelloWorld;

public class Spring01Test {
	
	
	// 测试通过构造器进行依赖注入
	@Test
	public void diByConstructorTest() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		// 获取基于构造器1 的依赖注入bean实例
		Car car = (Car) ctx.getBean("car");
		System.out.println(car);
		// 获取基于构造器2 的依赖注入bean实例
		car = (Car) ctx.getBean("car2");
		System.out.println(car);
	}
	
	// spring测试用例 
	@Test
	public void hello01Test() {
		/*// 创建helloworld的一个对象
		HelloWorld hw = new HelloWorld();
		// 为name属性赋值 
		hw.setName("lisi");
		// 调用 HelloWorld 对象的方法
		hw.hello();*/
		
		// 1、创建 spring ioc容器对象
		// ApplicationContext 代表ioc 容器；
		// ClassPathXmlApplicationContext： 是 ApplicationContext接口的实现类。 
		// 该实现类从类路径下加载配置文件；
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 2、从 ioc 容器中获取 bean
		// 方法1_利用id 获取 ioc 容器中的 bean
//		HelloWorld hw = (HelloWorld) ctx.getBean("helloWorld");
		// 方法2_利用 Class 获取 ioc 容器中的 bean， 缺点是必须只能有一个HelloWorld类型的 bean
		HelloWorld hw = (HelloWorld) ctx.getBean(HelloWorld.class);  
		// 3、调用 hello 方法
		hw.hello();
	}
}
