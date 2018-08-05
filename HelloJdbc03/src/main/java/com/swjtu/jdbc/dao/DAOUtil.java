package com.swjtu.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.swjtu.jdbc.utils.JdbcUtil;
import com.swjtu.jdbc.utils.ReflectionUtil;

public class DAOUtil {
	/*insert update delete 操作都可以包含在里面，
	且通过 statement.executeUpdate() 来实现；*/
	public void update(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		
		try {
			conn = JdbcUtil.getConnection();
			stat = conn.prepareStatement(sql);
			int i = 1;
			for (Object arg : args) {
				stat.setObject(i++, arg);
			}
			int updRows = stat.executeUpdate();
			System.out.println("更新的行数 = " + updRows);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(stat, conn, null);
		}
	}
	
	/*查询一条记录*/
	public <T> T getSingle(Class<T> clazz, String sql, Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		T instance  = null;
		try {
			conn = JdbcUtil.getConnection();
			stat = conn.prepareStatement(sql);
			int i = 1;
			for (Object arg : args) {
				stat.setObject(i++, arg);
			}
			rs = stat.executeQuery();
			instance = clazz.newInstance();
			ResultSetMetaData metaData = rs.getMetaData();
			while(rs.next()) {
				for (i = 0; i < metaData.getColumnCount(); i++) {
					String label = metaData.getColumnLabel(i+1);
					Object value = rs.getObject(label);
					ReflectionUtil.setFieldValue(instance, label, value);
				}
			}
			return instance;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(stat, conn, rs);
		}
		return null;
	}
	
	/*查询多条记录*/
	public <T> List<T> getList(Class<T> clazz, String sql, Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<T> list  = new ArrayList<T>();
		try {
			conn = JdbcUtil.getConnection();
			stat = conn.prepareStatement(sql);
			int i = 1;
			for (Object arg : args) {
				stat.setObject(i++, arg);
			}
			rs = stat.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			while(rs.next()) {
				T instance = clazz.newInstance(); // 通过反射创建对象 
				for (i = 0; i < metaData.getColumnCount(); i++) {
					String label = metaData.getColumnLabel(i+1);
					Object value = rs.getObject(label);
					ReflectionUtil.setFieldValue(instance, label, value);
				}
				list.add(instance);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(stat, conn, rs);
		}
		
		return null;
	}
	
	/*返回某条记录的某一个字段的值或一个统计的值（一共有多少条记录）*/
	public <E> E getForValue(String sql, Object... args) {
		
		return null;
	}
	
}
