package com.swjtu.spring.beans.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 自定义的 FactoryBean， 需要实现spring提供的 FactoryBean接口
 */
public class CarFactoryBean implements FactoryBean<Car>{
	private String brand;
	
	// 返回bean的对象
	@Override
	public Car getObject() throws Exception {
		return new Car(brand, 500000);
	}
	// 返回bean的类型
	@Override
	public Class<?> getObjectType() {
		return Car.class;
	}
	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
}
