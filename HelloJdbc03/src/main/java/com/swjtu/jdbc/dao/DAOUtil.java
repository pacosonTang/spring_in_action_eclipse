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
	public int update(String sql, Object... args) {
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
			return updRows;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(stat, conn, null);
		}
		return 0;
	}
	
	/*查询一条记录*/
	public <T> T getSingle(Class<T> clazz, String sql, Object... args) {
		List<T> list = this.getList(clazz, sql, args);
		
		if (list.size() != 1) {
			try {
				throw new Exception("查询单条记录失败！！查询得到的记录数目大于1！！");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return list.get(0);
		}
	}
	
	/*查询多条记录*/
	public <T> List<T> getList(Class<T> clazz, String sql, Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			stat = conn.prepareStatement(sql);
			int i = 1;
			for (Object arg : args) {
				stat.setObject(i++, arg);
			}
			rs = stat.executeQuery();
			/*获得结果集对应的bean列表*/
			return this.getBeanList(clazz, rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(stat, conn, rs);
		}
		return null;
	}
	/**
	 * 获得结果集对应的bean列表
	 * @param clazz
	 * @param rs
	 * @return list
	 * @throws Exception
	 */
	public <T> List<T> getBeanList(Class<T> clazz, ResultSet rs) throws Exception {
		List<T> list  = new ArrayList<T>();
		/*获取结果集的列名列表*/
		List<String> columnLabelList = this.getColumnLabelList(rs);
		while(rs.next()) {
			T instance = clazz.newInstance(); // 通过反射创建对象 
			for(String label : columnLabelList) {
				Object value = rs.getObject(label);
				ReflectionUtil.setFieldValue(instance, label, value);
			}
			list.add(instance);
		}
		return list;
	}
	
	/**
	 * 获取结果集的列名列表
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public List<String> getColumnLabelList(ResultSet rs) throws Exception {
		List<String> list = new ArrayList<>();
		
		ResultSetMetaData metaData = rs.getMetaData();
		for (int i = 0; i < metaData.getColumnCount(); i++) {
			String label = metaData.getColumnLabel(i+1);
			list.add(label);
		}
		
		return list;
	}
	
	
	
	
	/*返回某条记录的某一个字段的值或一个统计的值（一共有多少条记录）*/
	public Object getForValueWithSingleField(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			stat = conn.prepareStatement(sql);
			int i = 1;
			for (Object arg : args) {
				stat.setObject(i++, arg);
			}
			rs = stat.executeQuery();
			/*获得结果集对应的bean列表*/
			if (rs.next()) {
				return rs.getObject(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(stat, conn, rs);
		}
		return null;
	}
	
}
