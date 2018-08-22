package com.swjtu.spring.tx;

import org.springframework.stereotype.Service;

@Service
public interface BookStoreService {
	
	public void purchase(String userName, String isbn);
}
