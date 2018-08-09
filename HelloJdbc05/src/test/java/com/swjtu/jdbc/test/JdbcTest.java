package com.swjtu.jdbc.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import org.junit.Test;

import com.swjtu.jdbc.utils.JdbcUtil;



public class JdbcTest {
	
	/**
	 * 如何使用 jdbc 调用存储过程 或 存储函数 
	 */
	@Test
	public void testCallableStatement() {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			// 1. 通过 Connection 对象的 prepareCall()
			// 方法创建一个 CallableStatement 对象的实例.
			// 在使用 Connection 对象的 preparedCall() 方法时,
			// 需要传入一个 String 类型的字符串, 该字符串用于指明如何调用存储过程.
			conn = JdbcUtil.getConnection();
			String sql = "{call user_count_pro(?)}"; // 调用存储过程的sql
			cs = conn.prepareCall(sql);
			
			// 2. 通过 CallableStatement 对象的 reisterOutParameter() 方法注册 OUT 参数.
			cs.registerOutParameter(1, Types.INTEGER);
			
			// 3. 通过 CallableStatement 对象的 setXxx() 方法设定 IN 或 IN OUT 参数. 若想将参数默认值设为
			// null, 可以使用 setNull() 方法.
//			cs.setInt(1, );
			
			// 4. 通过 CallableStatement 对象的 execute() 方法执行存储过程
			cs.execute();
			
			// 5. 如果所调用的是带返回参数的存储过程, 
			//还需要通过 CallableStatement 对象的 getXxx() 方法获取其返回值.
			int count = cs.getInt(1); // 获取 user_tbl 的记录数目
			System.out.println(count);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(cs, conn, null);
		}
		
		
		
		
		
		
		
		
		
	}
}
