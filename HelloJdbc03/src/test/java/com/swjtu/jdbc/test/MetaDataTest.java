package com.swjtu.jdbc.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import org.junit.Test;

import com.swjtu.jdbc.utils.JdbcUtil;

public class MetaDataTest {
	
	/**
	 * DatabaseMetaData 是描述数据库元数据对象；
	 * 可以由 Connection 得到。
	 * 了解。
	 * 
	 */
	@Test	
	public void testDatabaseMetaData() {
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtil.getConnection();
			DatabaseMetaData metaData = conn.getMetaData();
			
			/*可以得到数据库本身的一些基本信息*/
			int majorVersion = metaData.getDatabaseMajorVersion();
			System.out.println("主版本号 = " + majorVersion);
			
			/*数据库用户名*/
			String userName = metaData.getUserName();
			System.out.println("用户名 = " + userName);
			
			/*得到mysql中有哪些数据库*/
			rs = metaData.getCatalogs();
			System.out.print("数据库列表有： ");
			while(rs.next()) {
				System.out.print(rs.getString(1) + ", ");
			}
			System.out.println();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(null, conn, null);
		}
	}
}
