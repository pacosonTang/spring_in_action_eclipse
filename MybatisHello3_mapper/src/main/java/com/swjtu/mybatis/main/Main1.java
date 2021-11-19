package com.swjtu.mybatis.main;

import java.util.*;

import com.swjtu.mybatis.utils.busi.CommonUtils;
import com.swjtu.mybatis.utils.busi.Env;

import com.swjtu.mybatis.bean.BusiClock;
import com.swjtu.mybatis.dao.mybatis.cwn.MYCwnOprDAO;

public class Main1 {
	public static void main(String[] args) {
		main1();   
	}
	
	public static void main1() {
		/* 指定环境，默认DEV */
		CommonUtils.setRunEnv(Env.ST);

		String flag = "1001B";
		List<Map<String, Object>> list = new ArrayList<>();
		BusiClock clock = new BusiClock();
		for (int i = 0; i < 10; i++) {
			String indexFormat = String.format("%8d", i);
			Map<String, Object> map = new HashMap<>(); 
			map.put("RCRD_ID", UUID.randomUUID());
			map.put("CUST_NUM", "CUST_NUM" +flag+ indexFormat);
			map.put("CUST_NAME", "CUST_NAME" +flag+ indexFormat);
			map.put("WARN_TIMES", i); 
			list.add(map);
		}
		MYCwnOprDAO.insert2CustWarn(list, "");
		System.out.println("bingo!");
		System.out.println("cost in second = " + clock.getCostOfSeconds());
		System.out.println("cost in milli = " + clock.getCostOfMillis());
	} 
}
