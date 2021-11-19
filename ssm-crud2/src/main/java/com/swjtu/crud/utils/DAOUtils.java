package com.swjtu.crud.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DAOUtils {
	
	private static String resource = "mybatis-config.xml";
	
	/**
	 * 获取SqlSessionFactory 
	 * @return
	 */
	private static SqlSessionFactory getSqlSessionFactory() {
		
		InputStream inputStream; 
		try { 
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			return sqlSessionFactory;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取 SqlSession 
	 * @return
	 */
	public static SqlSession s() {
		
		return getSqlSessionFactory().openSession(false);
	}
	
	/**
	 * 查询列表
	 */
	public static List<Map<String, Object>> selectList(String sql, Map<String, Object> param) {
		
		try {
			return s().selectList(sql, param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			s().close();
		}
		return Collections.emptyList(); 
	}
}
