package com.swjtu.spring.tx;

public class BookStockException extends RuntimeException{

	/**
	 * 序列化编号 
	 */
	private static final long serialVersionUID = 8574931024192753644L;

	public BookStockException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookStockException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BookStockException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BookStockException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BookStockException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
