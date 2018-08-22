package com.swjtu.spring.tx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("cashier")
public class CashierImpl implements Cashier{
	@Autowired
	private BookStoreService bookStoreService; 
	
	// 添加事务注解 
	// 1、REQUIRES_NEW 使用自己的事务， 调用方法的事务被挂起；
	// 即 checkoutWithREQUIRED 与 purchase 方法属于 不同的事务
	// 2、使用 isolation 指定事务的隔离级别，最常用取值为 读已提交  READ_COMMITTED
	// 默认情况下 spring 的声明式事务对所有的运行时异常进行回滚；
	// 也可以通过属性进行设置；通常情况下取默认值即可；
	// 3、如 noRollbackFor 指定不对哪个异常进行回滚。
	// 4、使用 readOnly 指定事务是否为 只读；只读事务属性: 
	// 表示这个事务只读取数据但不更新数据, 这样可以帮助数据库引擎优化事务.
	// 若真的是一个只读方法，已设置 readOnly=true,
	// 5、使用 timeout 指定强制回滚之前事务可以占用的时间； 
	
//	@Transactional(propagation=Propagation.REQUIRES_NEW
//			, isolation=Isolation.READ_COMMITTED
//			, noRollbackFor={AccountException.class, BookStockException.class})
	
	@Transactional(propagation=Propagation.REQUIRES_NEW
	, isolation=Isolation.READ_COMMITTED
	, noRollbackFor={AccountException.class, BookStockException.class}
	, readOnly=false
	, timeout=3) // 使用 timeout 指定强制回滚之前事务可以占用的时间；这里是3秒 
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
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void checkoutWithREQUIRED(String userName, List<String> isbns) {
		// 购买多本书 
		for (String isbn : isbns) {
			bookStoreService.purchase(userName, isbn);
		}
	}
	
}
