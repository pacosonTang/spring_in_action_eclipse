package com.swjtu.spring.beans.spel;

public class Person {
	private String name;
	private Car car;
	// city 引用 address bean的city属性
	private String city;
	// 根据car的price属性确定info：price大于等于30万，info为金领，否则为白领；
	private String info;
	
	public Person(){}
	
	public Person(String name, Car car, String city) {
		this.name = name;
		this.car = car;
		this.city = city;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", car=" + car + ", city=" + city + "]";
	}
}
