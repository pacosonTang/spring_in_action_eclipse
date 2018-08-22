package com.swjtu.spring.tx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("cashier")
public class CashierImpl implements Cashier{

	@Autowired
	private BookStoreService bookStoreService; 
	
	// 添加事务注解 
	// 使用 propagation 指定事务的传播行为， 
	// 即当前的事务 方法被另外一个事务方法调用时，
	// 如何使用事务？  默认取值为 REQUIRED ，即使用调用方法的事务 
	// REQUIRES_NEW 使用自己的事务， 调用方法的事务被挂起；
	// 即 checkoutWithREQUIRED 与 purchase 方法属于 不同的事务
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void checkoutWithREQUIRES_NEW(String userName, List<String> isbns) {
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
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void checkoutWithREQUIRED(String userName, List<String> isbns) {
		// 购买多本书 
		for (String isbn : isbns) {
			bookStoreService.purchase(userName, isbn);
		}
	}
	
}
