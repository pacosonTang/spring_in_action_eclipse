package com.swjtu.spring.beans.collection;

import java.util.List;

import com.swjtu.spring.beans.Car;

// 带有集合属性 cars 的RichPerson类 
public class RichPerson {
	private String name;
	private int age;
	private List<Car> cars;
	
	public RichPerson() {
	}
	
	public RichPerson(String name, int age) {
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
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	@Override
	public String toString() {
		String result = "";
		for(Car car : cars) {
			result += car.toString() + ", ";
		}
		// TODO Auto-generated method stub
		return "person info = { " 
				+ "name = " + this.name 
				+", age = " + this.age  
				+", cars[] = [" + result + "]";
	}
	
}
