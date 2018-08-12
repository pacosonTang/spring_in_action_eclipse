package com.swjtu.spring.beans;

import java.util.Properties;

// 数据源
public class MyDataSource {
	private Properties properties;

	public MyDataSource() {
	}
	
	public MyDataSource(Properties properties) {
		this.properties = properties;
	}
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "MyDataSource [properties=" + properties + "]";
	}
}
