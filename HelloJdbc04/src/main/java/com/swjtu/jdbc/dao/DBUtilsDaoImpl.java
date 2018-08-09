package com.swjtu.jdbc.dao;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.swjtu.jdbc.utils.ReflectionUtil;

public class DBUtilsDaoImpl<T> implements DBUtilsDao<T>{
	private QueryRunner queryRunner; 
	private Class<T> type;
	
	public DBUtilsDaoImpl() {
		queryRunner = new QueryRunner();
		// 这个 Class<T> 类型的实例 type 赋值有错误。
		type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]; 
	}
	
	@Override
	public void batch(Connection con, String sql, Object[]... args) {
		
	}

	@Override
	public <T> T getForValue(Connection con, String sql, Object... args) {
		return null;
	}

	@Override
	public List<T> queryList(Connection con, String sql, Object... args) {
		return null;
	}

	@Override
	public T query(Connection conn, String sql, Object... args) throws Exception {
		return queryRunner.query(conn, sql
				, new BeanHandler<T>(type)
				, args);
	}

	@Override
	public int update(Connection con, String sql, Object... args) {
		
		return 0;
	}
	
}
