package com.swjtu.spring.tx.xml;

/**
 *  书店的dao
 * @author pacoson
 */
public interface BookStoreDao {
	
	// 查询书籍单价
	public int findBookPriceByIsbn(String isbn);
	// 更新库存
	public int updateStock(String isbn);
	// 更新账户余额
	public int updateUserBalance(String userName, int price);
}
