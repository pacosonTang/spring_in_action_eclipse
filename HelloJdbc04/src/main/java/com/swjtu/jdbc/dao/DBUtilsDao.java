package com.swjtu.jdbc.dao;

import java.sql.Connection;
import java.util.List;

/**
 * 访问数据库的dao接口
 * 里边定义好 访问数据表的 各种方法
 * @param T: dao处理的实体类的类型 
 * @author pacoson
 */
public interface DBUtilsDao<T> {
	
	/**
	 * 批量处理
	 * @param con
	 * @param sql
	 * @param args 填充占位符的Object[]类型的 可变参数
	 */
	void batch(Connection con, String sql, Object[]... args);
	
	/**
	 * 返回某个列的值， 如总人数 平均工资 某个人的 email 等
	 * @param con
	 * @param sql
	 * @param args
	 * @return
	 */
	<T> T getForValue(Connection con, String sql, Object... args);
	
	/**
	 * 查询多个对象或对象列表
	 * @param con
	 * @param sql
	 * @param args
	 * @return
	 */
	List<T> queryList(Connection con, String sql, Object... args);
	
	/**
	 * 查询单个对象
	 * @param con
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception 
	 */
	T query(Connection con, String sql, Object... args) throws Exception;
	
	/**
	 * 更新操作， 包括 insert, update, delete 
	 * @param con 数据库连接
	 * @param sql sql语句
	 * @param args 占位符的可变参数 
	 * return 更新记录数目
	 */
	int update(Connection con, String sql, Object... args);
}
