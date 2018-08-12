package com.swjtu.spring.beans.collection;

import java.util.List;
import java.util.Map;

import com.swjtu.spring.beans.Car;

// 带有集合属性 cars 的RichPerson类 
public class MapPerson {
	private String name;
	private int age;
	private Map<String, Car> cars;
	
	public MapPerson() {
	}
	
	public MapPerson(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Map<String, Car> getCars() {
		return cars;
	}

	public void setCars(Map<String, Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		String result = "";
		for(String key : cars.keySet()) {
			result += "[key = " + key + ", value = " + cars.get(key) + "], ";
		}
		// TODO Auto-generated method stub
		return "person info = { " 
				+ "name = " + this.name 
				+", age = " + this.age  
				+", Map<String, Car> cars= [" + result + "]";
	}
	
}
