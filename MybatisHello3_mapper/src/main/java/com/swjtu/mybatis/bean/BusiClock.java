package com.swjtu.mybatis.bean;

public class BusiClock {
	private long start;
	
	public BusiClock() {
		start = System.currentTimeMillis();
	}
	public int getCostOfMillis() {
		return (int)(System.currentTimeMillis() - start);
	}
	public int getCostOfSeconds() {
		return getCostOfMillis()/1000; 
	}
}
