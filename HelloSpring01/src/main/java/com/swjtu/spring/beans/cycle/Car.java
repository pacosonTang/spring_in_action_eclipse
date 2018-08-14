package com.swjtu.spring.beans.cycle;

public class Car {
	private String brand;
	
	public Car() {
		System.out.println("car's default constructor.");
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		System.out.println("set brand property.");
		this.brand = brand;
	}
	
	public void init() {
		System.out.println("init method.....");
	}
	
	public void destory() {
		System.out.println("destory method.....");
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + "]";
	}
}
