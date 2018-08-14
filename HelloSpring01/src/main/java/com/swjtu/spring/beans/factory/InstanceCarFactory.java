package com.swjtu.spring.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 实例工厂方法：实例工厂的方法。
 * 即先需要创建工厂本身，在调用工厂的实例方法来返回bean 的实例
 */
public class InstanceCarFactory {
	private static Map<String, Car> cars;
	
	public InstanceCarFactory() {
		cars = new HashMap<>();
		cars.put("audi", new Car("audi", 300000));
		cars.put("ford", new Car("ford", 400000));
	}
	
	/**
	 * 实例工厂方法 
	 * @param name
	 * @return
	 */
	public Car getCar(String name) {
		return cars.get(name);
	}
}
