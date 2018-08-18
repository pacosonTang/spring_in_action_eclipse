package com.swjtu.spring.aop.helloworld;

// 带日志的数学计算器
public class ArithmeticCalculatorLoggingImpl implements ArithmeticCalculator {
	// 加法 
	@Override
	public int add(int i, int j) {
		System.out.println("log: the method add" + " i = " + i + ", j = " + j);
		int result = i + j;
		System.out.println("log: i + j = " + result);
		return result;
	}
	// 减法 
	@Override
	public int sub(int i, int j) {
		System.out.println("log: the method sub" + " i = " + i + ", j = " + j);
		int result = i - j;
		System.out.println("log: i - j = " + result);
		return result;
	}
	// 乘法 
	@Override
	public int mul(int i, int j) {
		System.out.println("log: the method mul" + " i = " + i + ", j = " + j);
		int result = i * j;
		System.out.println("log: i * j = " + result);
		return result;
	}
	// 除法 
	@Override
	public int div(int i, int j) {
		System.out.println("log: the method div" + " i = " + i + ", j = " + j);
		int result = i / j;
		System.out.println("log: i / j = " + result);
		return result;
	}
	
}
