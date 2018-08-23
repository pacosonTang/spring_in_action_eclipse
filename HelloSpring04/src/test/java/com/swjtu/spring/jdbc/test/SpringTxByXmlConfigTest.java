package com.swjtu.spring.jdbc.test;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.swjtu.spring.tx.xml.BookStoreDao;
import com.swjtu.spring.tx.xml.service.BookStoreService;
import com.swjtu.spring.tx.xml.service.Cashier;

/**
 * 通过xml配置文件的方式 配置事务 
 * @author pacoson
 *
 */
public class SpringTxByXmlConfigTest {
	
	private ApplicationContext ctx;
	private JdbcTemplate jdbcTemplate;
	private BookStoreDao bookStoreDao;
	private BookStoreService bookStoreService;
	private Cashier cashier;
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext-tx-xml.xml");
		jdbcTemplate = ctx.getBean(JdbcTemplate.class);
		bookStoreDao = ctx.getBean(BookStoreDao.class);
		bookStoreService = ctx.getBean(BookStoreService.class);
		cashier = ctx.getBean(Cashier.class);
	}
	
	/**
	 * 测试 事务的传播行为 
	 * ，传播行为是  @Transactional(propagation=Propagation.REQUIRED) 
	 */
	@Test
	public void testTransactionalPropagationWithREQUIRED() {
		cashier.checkoutWithREQUIRED("zhangsan", Arrays.asList("1001", "1002"));
	}
	
	/**
	 * 测试 bookStoreService 买书的荔枝
	 */
	@Test
	public void testBookStoreService() {
		String userName = "zhangsan";
		String isbn = "1001";
		bookStoreService.purchase(userName, isbn);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
