package com.swjtu.spring.tx.xml.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.swjtu.spring.tx.xml.service.BookStoreService;
import com.swjtu.spring.tx.xml.service.Cashier;

public class CashierImpl implements Cashier{
	
	private BookStoreService bookStoreService; 

	public void setBookStoreService(BookStoreService bookStoreService) {
		this.bookStoreService = bookStoreService;
	}

//	@Transactional(propagation=Propagation.REQUIRES_NEW
//	, isolation=Isolation.READ_COMMITTED
//	, noRollbackFor={AccountException.class, BookStockException.class}
//	, readOnly=false
//	, timeout=3) // 使用 timeout 指定强制回滚之前事务可以占用的时间；这里是3秒 
	@Override
	public void checkoutWithREQUIRES_NEW(String userName, List<String> isbns) {
		try {
			Thread.sleep(5000);// 睡眠5秒
//			Thread.sleep(1000);// 睡眠1秒
		} catch (Exception e) {
		}
		// 购买多本书 
		for (String isbn : isbns) {
			bookStoreService.purchase(userName, isbn);
		}
	}
	
	// 添加事务注解 
	// 使用 propagation 指定事务的传播行为， 
	// 即当前的事务 方法被另外一个事务方法调用时，
	// 如何使用事务？  默认取值为 REQUIRED ，即使用调用方法的事务
	// 即 checkoutWithREQUIRED 与 purchase 方法属于同一个事务
//	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void checkoutWithREQUIRED(String userName, List<String> isbns) {
		// 购买多本书 
		for (String isbn : isbns) {
			bookStoreService.purchase(userName, isbn);
		}
	}
	
}
