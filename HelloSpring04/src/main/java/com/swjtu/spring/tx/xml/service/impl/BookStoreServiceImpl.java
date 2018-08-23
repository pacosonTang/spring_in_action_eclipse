package com.swjtu.spring.tx.xml.service.impl;

import com.swjtu.spring.tx.xml.BookStoreDao;
import com.swjtu.spring.tx.xml.service.BookStoreService;

public class BookStoreServiceImpl implements BookStoreService{
	private BookStoreDao bookStoreDao;
	
	public void setBookStoreDao(BookStoreDao bookStoreDao) {
		this.bookStoreDao = bookStoreDao;
	}

	// 添加事务注解 @Transactional ，使其内部操作构成一个事务
//	@Transactional
	@Override
	public void purchase(String userName, String isbn) {
		// 查询价格
		int price = bookStoreDao.findBookPriceByIsbn(isbn);
		// 更新库存 
		bookStoreDao.updateStock(isbn);
		// 更新账户余额 
		bookStoreDao.updateUserBalance(userName, price);
	}
}
