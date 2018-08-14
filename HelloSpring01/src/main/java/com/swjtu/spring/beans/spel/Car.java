package com.swjtu.spring.beans.spel;

public class Car {
	private String brand;
	private double price;
	// 轮胎周长
	private double wheelSize;
	
	public Car() {
		System.out.println("car's default constructor with no parameters.");
	}
	
	public Car(String brand, double price, double wheelSize) {
		this.brand = brand;
		this.price = price;
		this.wheelSize = wheelSize;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	public double getWheelSize() {
		return wheelSize;
	}
	public void setWheelSize(double wheelSize) {
		this.wheelSize = wheelSize;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", price=" + price + ", wheelSize="
				+ wheelSize + "]";
	}
}
