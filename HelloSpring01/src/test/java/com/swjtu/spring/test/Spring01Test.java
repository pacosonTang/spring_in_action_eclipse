package com.swjtu.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swjtu.spring.beans.HelloWorld;

public class Spring01Test {
	
	@Test
	public void hello01Test() {
		/*// 创建helloworld的一个对象
		HelloWorld hw = new HelloWorld();
		// 为name属性赋值 
		hw.setName("lisi");
		// 调用 HelloWorld 对象的方法
		hw.hello();*/
		
		// 创建 spring ioc容器对象
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 从 ioc 容器中获取 bean
		HelloWorld hw = (HelloWorld) ctx.getBean("helloWorld");
		// 调用 hello 方法
		hw.hello();
	}
}
