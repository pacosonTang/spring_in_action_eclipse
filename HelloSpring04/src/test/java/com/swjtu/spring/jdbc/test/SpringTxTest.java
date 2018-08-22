package com.swjtu.spring.jdbc.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.swjtu.spring.tx.BookStoreDao;
import com.swjtu.spring.tx.BookStoreService;

/**
 * spring jdbcTemplate 的测试用例
 * @author pacoson
 *
 */
public class SpringTxTest {
	
	private ApplicationContext ctx;
	private JdbcTemplate jdbcTemplate;
	private BookStoreDao bookStoreDao;
	private BookStoreService bookStoreService;
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = ctx.getBean(JdbcTemplate.class);
		bookStoreDao = ctx.getBean(BookStoreDao.class);
		bookStoreService = ctx.getBean(BookStoreService.class);
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
	
	
	/**
	 * 更新账户余额
	 */
	@Test
	public void testUpdateUserBalance() {
		String userName = "zhangsan";
		int price = 1000000;
		int updRows = bookStoreDao.updateUserBalance(userName, price);
		System.out.println("更新记录行数 = " + updRows);
	}
	
	/**
	 * 更新库存
	 */
	@Test
	public void testUpdateStock() {
		String isbn = "1001";
		int updRows = bookStoreDao.updateStock(isbn);
		System.out.println("更新记录行数 = " + updRows);
	}
	
	/**
	 * 查询书籍单价
	 */
	@Test
	public void testFindBookPriceByIsbn() {
		String isbn = "1001";
		int price = bookStoreDao.findBookPriceByIsbn(isbn);
		System.out.println("isbn为 " + isbn +" 的书籍的单价为 " + price);
	}
	
}
