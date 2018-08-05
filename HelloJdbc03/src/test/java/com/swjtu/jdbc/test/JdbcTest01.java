package com.swjtu.jdbc.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

import com.mysql.jdbc.Driver;
import com.swjtu.jdbc.bean.Student;
import com.swjtu.jdbc.bean.User;
import com.swjtu.jdbc.utils.JdbcUtil;
import com.swjtu.jdbc.utils.ReflectionUtil;

public class JdbcTest01 {
	
	/**
	 * 读取blob类型的数据
	 * 调用 getBlob(int index) 
	 */
	@Test
	public void testQueryBlob() {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = JdbcUtil.getConnection();
			String sql = "select user_name, password, picture from user_tbl where password = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setObject(1, "house");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				java.sql.Blob picture = rs.getBlob(3); // 读取 java.sql.Blob 对象
				System.out.println(picture);
				// 把 picture 写到硬盘上
				InputStream in = picture.getBinaryStream();
				OutputStream os = new FileOutputStream("D:\\temp\\note\\house_out.jpg");
				
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					os.write(buffer);
				}
				os.close();
				in.close();
			}
			System.out.println("查询blob完成！！");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(ps, conn, null);
		}
	}
	
	/**
	 * 插入blob类型的数据， 必须使用 PreparedStatement， 因为
	 * blob类型的数据是无法使用 字符串拼接的。
	 * 
	 * 调用 setBlob(int index, InputStream inputStream) 
	 */
	@Test
	public void testInsertBlob() {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = JdbcUtil.getConnection();
			String sql = "insert into user_tbl (user_name, password, picture) values(?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setObject(1, "house");
			ps.setObject(2, "house");
			InputStream is = new BufferedInputStream(new FileInputStream("D:\\temp\\note\\house.jpg"));
			ps.setBlob(3, is); // 插入 blob 数据类型的字段 
			int updRows = ps.executeUpdate();
			System.out.println("更新记录行数 = " + updRows);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(ps, conn, null);
		}
	}
	
	/**
	 * 通过jdbc取得数据库自动生成的主键
	 */
	@Test
	public void testGetPrimaryKey() {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = JdbcUtil.getConnection();
			String sql = "insert into user_tbl (user_name, password) values(?, ?)";
			
			/*取得数据库自动生成的主键*/
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, "tianqi");
			ps.setObject(2, "tianqi");
			int updRows = ps.executeUpdate();
			System.out.println("更新记录行数 = " + updRows);
			
			// 通过方法 getGeneratedKeys 返回数据库自动生成的主键列表
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				System.out.println("数据库自动生成的主键 = " + rs.getObject(1));
			}
			/* 获取结果集的元数据 */
			ResultSetMetaData metaData = rs.getMetaData();
			for (int i = 0; i < metaData.getColumnCount(); i++) {
				// PreparedStatement.getGeneratedKeys 返回的结果集中只有一列，列名为 GENERATED_KEY
				System.out.println("数据库自动生成的主键的列名为 = " + metaData.getColumnName(i+1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(ps, conn, null);
		}
	}
	
	// 将以上两个方法（getUser 和  getStudent）合并为一个方法
	
	/**
	 * 通过预编译sql和反射查询对象实例 
	 * @param sql
	 * @param args
	 * @return
	 */
	public static <T> T getInstanceByReflection(Class<T> clazz, String sql, Object... args) {
		T instance = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = JdbcUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			int i = 1;
			for (Object arg : args) {
				preparedStatement.setObject(i++, arg);
			}
			/*通过反射创建对象*/
			instance = clazz.newInstance();
			/* 查询用户信息 */ 
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				// 得到 ResultSetMetaData 对象
				ResultSetMetaData metaData = rs.getMetaData();
				// 打印每一个列名或列别名
				for (i = 1; i <= metaData.getColumnCount(); i++) {
					String colLabel = metaData.getColumnLabel(i); // userName 和 password
					Object colValue = rs.getObject(colLabel); // 列的属性值
					/*通过反射设置对象instance的属性值*/
					ReflectionUtil.setFieldValue(instance, colLabel, colValue);  
				}
			} 
			return instance;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(preparedStatement, connection, rs);
		}
		return instance;
	}
	
	/**
	 * 通过预编译sql语句查询 User
	 * @param sql
	 * @param args
	 * @return
	 */
	public static User getUser(String sql, Object... args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			int i = 1;
			for (Object arg : args) {
				preparedStatement.setObject(i++, arg);
			}
			/* 查询用户信息 */ 
			resultSet = preparedStatement.executeQuery(); 
			
			User user = new User();
			if (resultSet.next()) {
				user.setUserName(resultSet.getString(1));
				user.setPassword(resultSet.getString(2));
				return user;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(preparedStatement, connection, resultSet);
		}
		return null;
	}
	
	/**
	 * 通过预编译sql语句查询student
	 * @param sql
	 * @param args
	 * @return
	 */
	public static Student getStudent(String sql, Object... args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			int i = 1;
			for (Object arg : args) {
				preparedStatement.setObject(i++, arg);
			}
			/*查询学生信息*/
			resultSet = preparedStatement.executeQuery(); 
			
			Student stu = new Student();
			if (resultSet.next()) {
				stu.setStudentNum(resultSet.getString(1));
				stu.setExamRegistrationNum(resultSet.getString(2));
				stu.setIdCard(resultSet.getString(3));
				stu.setName(resultSet.getString(4));
				stu.setAge(resultSet.getInt(5));
				stu.setGrade(resultSet.getString(6));
				stu.setLoc(resultSet.getString(7));
				return stu;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(preparedStatement, connection, resultSet);
		}
		return null;
	}
	
	/**
	 * ResultSet:结果集，封装了使用 jdbc 进行查询的结果。
	 * 1、调用 statement.executeQuery(sql) 可以得到结果集；
	 * 2、ResultSet 返回的实际上就是一张数据表。有一个指针指向数据库表的第一行的前面；
	 * 可以调用 next() 方法检测是否有下一行。若有则返回true且指针下移，否则返回false。
	 * 相当于 Iterator.hasNext 和 next 方法的结合体
	 * 3、当指针定位到一行时，可以通过调用 getXXX(index) 或 getXXX(columnName) 获取每列的值。
	 * 如：getInt(1), getString("name")
	 * 4、ResultSet 当然也需要进行关闭。
	 */
	@Test
	public void testResultSet() {
		// 获取id = 20063 的emp记录并打印
		String sql = "SELECT id, last_name, gender, email, dept_id, status FROM emp_tbl where dept_id = '2'";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			stat = conn.createStatement();
			System.out.println("连接=" + conn + "，语句 = " + stat);
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				String lastName = rs.getString(2);
				String gender = rs.getString(3);
				String email = rs.getString(4);
				
				System.out.println("id=" + id + ", lastName=" + lastName + ", gender = " + gender + ", email = " + email);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConnAndResultSet(stat, conn, rs);
		}
	}
	
	/**
	 * 通用的更新方法：insert, update, delete
	 * 版本1
	 * @param sql
	 */
	public void updateV1(String sql) {
		Connection conn = null;
		Statement stat = null;
		try {
			// 1.获取数据库连接
			conn = JdbcUtil.getConnection();
			// 3.准备删除的sql语句
//			String sql = "delete from emp_tbl where id = '20064'";
			// 4.执行插入
			// 4.1 获取操作sql语句的statement对象； 
//			调用Connection的  createStatement() 方法来获取
			stat = conn.createStatement();
			System.out.println("连接=" + conn + "，语句 = " + stat);
			// 4.2 调用statement对象的 executeUpdate(sql) 执行sql语句进行插入
			int deleteRows = stat.executeUpdate(sql);
			System.out.println("删除记录行数：" + deleteRows);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeStatAndConn(stat, conn);
		}
	}
	/**
	 * 测试delete 删除语句  
	 */
	@Test
	public void testDeleteStatement() {
		Connection conn = null;
		Statement stat = null;
		try {
			// 1.获取数据库连接
			conn = getConnectionByDriverManager();
			// 3.准备删除的sql语句
			String sql = "delete from emp_tbl where id = '20064'";
			// 4.执行插入
			// 4.1 获取操作sql语句的statement对象； 调用Connection的  createStatement() 方法来获取
			stat = conn.createStatement();
			System.out.println("连接=" + conn + "，语句 = " + stat);
			// 4.2 调用statement对象的 executeUpdate(sql) 执行sql语句进行插入
			int deleteRows = stat.executeUpdate(sql);
			System.out.println("删除记录行数：" + deleteRows);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (null != stat) {
				try {
					stat.close(); // 关闭statement对象
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if (null != conn) {
				try {
					conn.close(); // 关闭连接
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 向指定的数据表中插入一条记录，
	 *  1、Statement 用于执行sql语句的对象；
	 *  1.1、通过 Connection.createStatement() 方法来获取
	 *  1.2、通过 executeUpdate(sql) 可以执行sql语句
	 *  1.3、sql语可以是 insert， update 或 delete， 但不能是 select
	 *  2、Connection， Statement 都是应用程序和数据库服务器的连接资源；使用后一定要关闭
	 *  （try-catch-finally块中关闭）
	 *  3、关闭顺序：先关闭后获取的，即先关闭 Statement 后关闭 Connection 
	 * @throws Exception
	 */
	@Test
	public void testInsertStatement() {
		Connection conn = null; Statement stat = null;
		try {
			// 1.获取数据库连接
			conn = JdbcUtil.getConnection();
			// 3.准备插入插入的sql语句
			String sql = "INSERT INTO emp_tbl(id, last_name, gender, email, dept_id)"
	                     +"VALUES(null, 'tangrong11', 'm', 'tangrong@qq.com', 1)";
			// 4.执行插入
			// 4.1 获取操作sql语句的statement对象； 调用Connection的  createStatement() 方法来获取
			stat = conn.createStatement();
			System.out.println("连接=" + conn + "，语句 = " + stat);
			// 4.2 调用statement对象的 executeUpdate(sql) 执行sql语句进行插入
			int insertRows = stat.executeUpdate(sql);
			System.out.println("插入记录行数：" + insertRows);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		  JdbcUtil.closeStatAndConn(stat, conn);
		}
	}
	
	@Test
	public void testGetConnectionByDriverManager() throws Exception {
		System.out.println("testGetConnectionByDriverManager conn = " + getConnectionByDriverManager());
	}
	/**
	 * 通过 DriverManager获取 数据库连接 Connection
	 * @throws Exception 
	 */
	public Connection getConnectionByDriverManager() throws Exception {

		/*加载数据库配置文件*/
		InputStream in = JdbcTest01.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties info = new Properties();
		info.load(in);
		
		Class.forName(info.getProperty("driver"));
		Connection conn = DriverManager.getConnection(info.getProperty("url"), info);
		return conn;
	}
	/**
	 * DriverManager 是驱动管理类，通过 DriverManager 获取 数据库连接：
	 * 1、可以通过重载的 getConnection() 获取数据库连接；比较方便；
	 * 2、DriverManager可以同时管理多个驱动程序：如注册了多个数据库连接，则调用 getConnection方法
	 * 时传入的参数不同，则返回不同的数据库连接。
	 * @throws Exception 
	 */
	@Test
	public void testDriverManager() throws Exception {
		// 读取类路径下的 jdbc.properties 文件并将其封装到 Properties中：
		InputStream in = JdbcTest01.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties props = new Properties();
		props.load(in);
		
		/*加载数据库驱动程序（加载多个驱动程序）*/
		Class.forName(props.getProperty("driver"));
//		Class.forName("oracle 的驱动名");
//		Class.forName("mysql 的驱动名");
		/*通过DriverManager获取数据库连接*/
		Connection conn = DriverManager.getConnection(props.getProperty("url"), props);
		System.out.println("DriverManager获取的连接 " + conn);
	}
	
	/**
	 * Driver 是一个接口:
	 * 数据库厂商必须提供Driver实现类的接口， 
	 * 能从其中获取数据库连接。
	 * @throws SQLException 
	 */
	@Test
	public void testDriver() throws Exception {

		/* 获取数据库连接  */
		Connection conn = getConnection();
		System.out.println("通过反射 获取的连接 " + conn);
	}
	
	/**
	 * 编写一个通用方法： 在不修改源程序的情况下，可以获取任何数据库的连接
	 * 解决方法：把数据库驱动Driver实现类的全类名， url, user, password 放入一个配置文件中， 
	 * 通过修改配置文件的方式实现和具体的数据库解耦。
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	
	public static Connection getConnection() throws Exception {
		
		// 读取类路径下的 jdbc.properties 文件并将其封装到 Properties中：
		InputStream in = JdbcTest01.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties props = new Properties();
		props.load(in);
		/* 通过反射获取驱动器Driver */
		Driver driver = (Driver) Class.forName(props.getProperty("driver")).newInstance();
		/* 获取数据库连接  */
		Connection conn = driver.connect(props.getProperty("url"), props);
		return conn;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
