package com.swjtu.spring.beans;

public class HelloWorld {
	private String name;
	
	public HelloWorld() {
		System.out.println("HelloWorld Constructor!!");
	}
	
	public void hello() {
		System.out.println("hello " + name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("setName " + name);
		this.name = name;
	}
}
