package com.swjtu.spring.beans.cycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

// bean的后置处理器需要实现 BeanPostProcessor 接口
public class MyBeanPostProcessor implements BeanPostProcessor {

	// 初始化方法之前被调用的
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("postProcessBeforeInitialization 初始化方法之前被调用的,   " 
			   + "bean = " + bean + " , beanName = "+ beanName);
		return bean;
	}
	//初始化方法之后被调用的
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("postProcessAfterInitialization 初始化方法之后被调用的,  " 
			+ "bean = " + bean + ", beanName = " + beanName);
		Car car = new Car();
		car.setBrand("Ford"); // 偷梁换柱 
		return car;
	}
}
