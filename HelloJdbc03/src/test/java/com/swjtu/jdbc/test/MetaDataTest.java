package com.swjtu.jdbc.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.Test;

import com.swjtu.jdbc.utils.JdbcUtil;

public class MetaDataTest {
	
	
	/**
	 * ResultSetMetaData 用于描述结果集的元数据。
	 * 可以得到结果集中的基本信息： 结果集中有哪些列， 列名， 列的别名。
	 */
	@Test
	public void testResultSetMetaData() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtil.getConnection();
			String sql = "select user_name as userName, password as password "
					+ "from user_tbl";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(); 
			/*得到 ResultSetMetaData */
			ResultSetMetaData metaData = rs.getMetaData();
			/*得到列的个数*/
			int colCount = metaData.getColumnCount();
			System.out.println("列的个数 = " + colCount);

			for (int i = 0; i < colCount; i++) {
				// 得到列名
				String colName = metaData.getColumnName(i+1);
				// 得到别名
				String colLabel = metaData.getColumnLabel(i+1);
				System.out.println("列名 = " + colName + ", 别名 = " + colLabel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(ps, conn, rs);
		}
	}
	
	/**
	 * DatabaseMetaData 是描述数据库元数据对象；
	 * 可以由 Connection 得到。了解。
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
