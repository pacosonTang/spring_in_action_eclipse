package com.swjtu.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swjtu.spring.beans.Car;
import com.swjtu.spring.beans.HelloWorld;
import com.swjtu.spring.beans.MyDataSource;
import com.swjtu.spring.beans.Person;
import com.swjtu.spring.beans.collection.MapPerson;
import com.swjtu.spring.beans.collection.RichPerson;

public class Spring01Test {
	
	// 测试  通过p命名空间为bean属性赋值 进行依赖注入
	@Test
	public void diByPNameSpace() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Person person = (Person) ctx.getBean("person5");
		System.out.println(person); 
	}
	
	// 测试 配置独立的集合bean并获取该独立集合的引用 以进行依赖注入
	@Test
	public void diBySeperateCollection() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		RichPerson richPerson2 = (RichPerson) ctx.getBean("richPerson2");
		System.out.println(richPerson2); 
	}
	
	// 测试赋值 Properties 集合属性 进行依赖注入
	@Test
	public void diByPropertiesCollectionProperty() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		MyDataSource dataSource = (MyDataSource) ctx.getBean("myDataSource");
		System.out.println(dataSource); 
	}
	
	// 测试赋值 Map 集合属性 进行依赖注入
	@Test
	public void diByMapCollectionProperty() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");

		MapPerson mapPerson = (MapPerson) ctx.getBean("mapPerson");
		System.out.println(mapPerson); 
	}
		
	// 测试赋值 List集合属性 进行依赖注入
	@Test
	public void diByListCollectionProperty() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");

		RichPerson richPerson = (RichPerson) ctx.getBean("richPerson");
		System.out.println(richPerson); 
	}
	
	// 测试赋值 级联属性 进行依赖注入
	@Test
	public void diByCascadeProperty() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		// 获取基于构造器1 的依赖注入bean实例
		Person person = (Person) ctx.getBean("person4");
		System.out.println(person);
	}
	
	// 测试赋值 null进行依赖注入
	@Test
	public void diByNullValue() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		// 获取基于构造器1 的依赖注入bean实例
		Person person = (Person) ctx.getBean("person3");
		System.out.println(person);
	}
	
	// 测试通过内部bean进行依赖注入
	@Test
	public void diByInnerBeanTest() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		// 获取基于构造器1 的依赖注入bean实例
		Person person = (Person) ctx.getBean("person2");
		System.out.println(person);
	}
		
	// 测试通过构造器进行依赖注入
	@Test
	public void diByConstructorAndRefTest() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		// 获取基于构造器1 的依赖注入bean实例
		Person person = (Person) ctx.getBean("person");
		System.out.println(person);
	}
	
	// 测试通过构造器进行依赖注入
	@Test
	public void diByConstructorTest() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		// 获取基于构造器1 的依赖注入bean实例
		Car car = (Car) ctx.getBean("car");
		System.out.println(car);
		// 获取基于构造器2 的依赖注入bean实例
		car = (Car) ctx.getBean("car2");
		System.out.println(car);
	}
	
	// spring测试用例 
	@Test
	public void hello01Test() {
		/*// 创建helloworld的一个对象
		HelloWorld hw = new HelloWorld();
		// 为name属性赋值 
		hw.setName("lisi");
		// 调用 HelloWorld 对象的方法
		hw.hello();*/
		
		// 1、创建 spring ioc容器对象
		// ApplicationContext 代表ioc 容器；
		// ClassPathXmlApplicationContext： 是 ApplicationContext接口的实现类。 
		// 该实现类从类路径下加载配置文件；
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 2、从 ioc 容器中获取 bean
		// 方法1_利用id 获取 ioc 容器中的 bean
//		HelloWorld hw = (HelloWorld) ctx.getBean("helloWorld");
		// 方法2_利用 Class 获取 ioc 容器中的 bean， 缺点是必须只能有一个HelloWorld类型的 bean
		HelloWorld hw = (HelloWorld) ctx.getBean("helloWorld");
		HelloWorld hw2 = (HelloWorld) ctx.getBean("helloWorld");
		System.out.println("hw = " + hw + ", hw2 = " +hw2);
		System.out.println("hw == hw2 ? " + (hw == hw2)); // true
		// 3、调用 hello 方法
		hw.hello();
	}
}
