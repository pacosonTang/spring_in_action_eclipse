package com.atguigu.crud.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;

/**
 * @author pacoson
 * 推荐spring的项目就可以使用 spring的单元测试，
 * 可以自动注入我们需要的组件
 * 1、导入spring test模块
 * 2、@ContextConfiguration 指定spring配置文件的位置，它自动创建出ioc容器
 * 3、直接autowired自动装配 要使用的组件即可
 */
@RunWith(SpringJUnit4ClassRunner.class) // 指定使用哪一个单元测试来测试
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	private EmployeeMapper empMapper;
	@Autowired
	private DepartmentMapper deptMapper;
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testEmpInsert() {
//		// 1、创建springIOC容器
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//		// 2、从容器中获取Mapper
//		Department dept = ioc.getBean(Department.class);
		// 插入几个员工
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class); // 批量
		for (int i = 100; i < 1000; i++) {
			mapper.insert(new Employee(i+"", "emp_id_" + i, "emp_name_" + i, "M", "email@_" + i));
		} 
		System.out.println("bingo!!"); 
	}
}

