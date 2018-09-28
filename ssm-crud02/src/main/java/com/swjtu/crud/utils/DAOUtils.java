package com.swjtu.crud.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DAOUtils {

	private static String resource = "mybatis-config.xml";
	private static SqlSessionFactory sqlSessionFactory;
	
	public static <T> T selectList(String statement, Map<String, Object> params) {
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (T) s().selectList(statement, params);
	}
	
	protected static SqlSession s() {
		return sqlSessionFactory.openSession();
	}
	
}
