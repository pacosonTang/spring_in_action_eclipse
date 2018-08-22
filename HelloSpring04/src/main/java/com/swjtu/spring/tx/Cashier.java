package com.swjtu.spring.tx;

import java.util.List;

public interface Cashier {
	public void checkoutWithREQUIRED(String userName, List<String> isbns);
	
	public void checkoutWithREQUIRES_NEW(String userName, List<String> isbns);
}
