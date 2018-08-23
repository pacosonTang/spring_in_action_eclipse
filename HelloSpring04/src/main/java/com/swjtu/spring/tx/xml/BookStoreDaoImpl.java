package com.swjtu.spring.tx.xml;

import org.springframework.jdbc.core.JdbcTemplate;

public class BookStoreDaoImpl implements BookStoreDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public BookStoreDaoImpl() {}
	
	@Override
	public int findBookPriceByIsbn(String isbn) {
		String sql = "select price from book_tbl where isbn = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, isbn);
	}

	@Override
	public int updateStock(String isbn) {
		// 检查书的库存是否足够，若不够，则抛出异常；
		String sql = "select stock from book_store_tbl where isbn = ?";
		int stock = jdbcTemplate.queryForObject(sql, Integer.class, "1001");
		if (stock < 1) {
			throw new BookStockException("库存不足");
		}
		
		sql = "update book_store_tbl set stock = stock - 1 where isbn = ?";
		int updRows = jdbcTemplate.update(sql, isbn);
		System.out.println("updateStock: updRows = " + updRows);
		return updRows;
	}

	@Override
	public int updateUserBalance(String userName, int price) {
		// 验证账户余额是否足够，若不足够，则抛出异常 
		String sql = "select balance from account_tbl where user_name = ?";
		int balance = jdbcTemplate.queryForObject(sql, Integer.class, userName);
		if (balance < price) {
			throw new BookStockException("余额不足");
		}
		
		sql = "update account_tbl set balance = balance - ? where user_name = ?";
		int updRows = jdbcTemplate.update(sql, price, userName);
		System.out.println("updateUserBalance: updRows = " + updRows);
		return updRows;
	}
}
