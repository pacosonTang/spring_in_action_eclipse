package com.swjtu.crud.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryManager {
	
	private String resource = "mybatis-config.xml";
	private SqlSessionFactory sqlSessionFactory;
	public final static SqlSessionFactoryManager INSTANCE = new SqlSessionFactoryManager();
	
	private SqlSessionFactoryManager() {
		
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
			.build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
