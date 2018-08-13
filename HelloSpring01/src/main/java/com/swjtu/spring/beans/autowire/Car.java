package com.swjtu.spring.beans.autowire;

public class Car {
	private String brand;
	private double price;
	
	public Car() {
	}
	
	public Car(String brand, double price) {
		this.brand = brand;
		this.price = price;
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

	@Override
	public String toString() {
		return "com.swjtu.spring.beans.autowire.Car [brand=" + brand + ", price=" + price + "]";
	}
}
