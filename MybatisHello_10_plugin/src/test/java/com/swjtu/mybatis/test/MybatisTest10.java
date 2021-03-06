package com.swjtu.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.swjtu.mybatis.bean.Employee;
import com.swjtu.mybatis.dao.EmployeeMapper;

public class MybatisTest10 {
	

	/**
	 * 测试插件开发：
	 * 
	 * 插件原理：在4大对象创建的时候， 
	 * （Executor、ParameterHandler、ResultSetHandler、StatementHandler）
	 * 
	 * 1、每个创建出来的对象不是直接返回的， 
	 *     而是 interceptorChain.pluginAll(resultSetHandler);
	 * 2、获取到所有的 Interceptor（target）拦截器；
	 *     调用pluginAll(target) 返回target被包装后的对象；
	 * 3、插件机制：我们可以使用插件为目标对象创建一个代理对象， aop（面向切面编程）
	 *     我们的插件可以为4个对象，创建出代理对象；
	 *     代理对象就可以拦截到4个对象的每一个执行方法；
	 * 
	 * public Object pluginAll(Object target) {
	    for (Interceptor interceptor : interceptors) {
	      target = interceptor.plugin(target);
	    }
	    return target;
	  }
	 * @throws IOException
	 */
	
	/**
	 * 插件开发：
	 * 
	 * 1、编写一个 插件 Interceptor的实现类：
	 * 2、使用 @Intercepts 注解 完成 插件签名；
	 * 3、将写好的 插件注册到 全局配置文件中；
	 * <!-- 注册插件到 mybatis全局配置中 -->
		<plugins>
			<plugin interceptor="com.swjtu.mybatis.dao.MyFirstPlugin">
				<property name="username" value="root"/>
				<property name="password" value="123456"/>
			</plugin>
		</plugins>
	 * 4、
	 * @throws IOException
	 */
	@Test
	public void testPlugin() throws IOException {
		/*获取 sqlSessionFactory 对象*/
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
		/*获取 SqlSession 对象*/
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			mapper.getEmployeeId(1);
			Employee emp = mapper.getEmployeeId(1);
			System.out.println(emp);
		} finally { 
			session.close();
		}
	}
	
	/**
	 * 1、获取 sqlSessionFactory 对象；
	 * 	      解析文件的每一个信息，保存在 Configuration中，返回包含Configuration的DefaultSqlSession；
	 *     注意：【MappedStatement】：代表一个增删改查的详细信息；
	 * 2、获取 SqlSession 对象；
	 *    	返回一个 DefaultSqlSession对象，该对象包含 Executor 和 Configuration。
	 *      会创建  Executor对象；
	 * 3、获取 接口的代理对象（MapperProxy）
	 *        getMapper方法，使用 MapperProxyFactory 创建一个 MapperProxy的代理对象， 
	 *        代理对象里边包含了 DefaultSqlSession(Executor)
	 * 4、执行增删改查 方法
	 * 
	 * 总结：
	 * 1、根据配置文件（全局或sql映射文件）初始化 Configuration对象；
	 * 2、创建一个 DefaultSqlSession对象， 该对象包含 Configuration对象以及 Executor对象（根据全局配置文件中的
	 *    defaultExecutorType创建出对应的Executor对象）
	 * 3、DefaultSqlSession.getMapper() 获取Mapper接口对应的 MapperProxy 对象；
	 * 4、MapperProxy对象有 DefaultSqlSession对象；
	 * 5、执行增删改查方法：
	 * 	   1)调用 defaultSqlSession对象的增删改查（最终会调用Executor的增删改查， SimpleExecutor对象）； 
	 *     2)Executor对象最终会创建一个 StatementHandler对象， parameterHandler对象和 resultSetHandler对象；
	 *     3)调用 statementHandler 的prepare() 和 parameterize() 方法 预编译sql语句；
	 *       使用 ParameterHandler 设置参数；
	 *     4)调用 statementHandler 的 增删改查方法；
	 *     5)使用 resultSetHandler对象 封装结果；
	 * 注意：
	 *     四大对象创建的时候，总是会调用  interceptorChain.pluginAll(resultSetHandler);
	 * @throws IOException
	 */ 
	@Test
	public void test2() throws IOException {
		/*获取 sqlSessionFactory 对象*/
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
		/*获取 SqlSession 对象*/
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			mapper.getEmployeeId(1);
			Employee emp = mapper.getEmployeeId(1);
			System.out.println(emp);
		} finally { 
			session.close();
		}
	}
	
	/**
	 * 获取SqlSessionFactory 
	 * @return
	 */
	public SqlSessionFactory getSqlSessionFactory() {
		String resource = "mybatis-config.xml";
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
}
