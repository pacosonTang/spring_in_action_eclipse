package com.swjtu.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookStoreServiceImpl implements BookStoreService{
	@Autowired
	private BookStoreDao bookStoreDao;
	
	// 添加事务注解 @Transactional ，使其内部操作构成一个事务
	@Transactional
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
