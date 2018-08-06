package com.swjtu.jdbc.bean;

import java.math.BigDecimal;

public class User {
	private String userName;
	private String password;
	private BigDecimal balance;
	
	public User(String userName, String password, BigDecimal balance) {
		this.userName = userName;
		this.password = password;
		this.balance = balance;
	}

	public User() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "User.toString() ==> userName = " + userName + ", password = " + password + ", balance = " + balance;
	}
	
	
}
