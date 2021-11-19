package com.swjtu.mybatis.main;

import com.swjtu.mybatis.bean.BusiClock;
import com.swjtu.mybatis.dao.mybatis.cwn.MYCwnOprDAO;
import com.swjtu.mybatis.dao.oscasedb.cwn.OCCwnOprDAO;
import com.swjtu.mybatis.utils.busi.CommonUtils;
import com.swjtu.mybatis.utils.busi.Env;

import java.util.*;

public class OCMain {
	public static void main(String[] args) {
		main1();
	}

	public static void main2() {
		/* 指定环境，默认DEV */
		CommonUtils.setRunEnv(Env.ST);

		List<Map<String, Object>> list = OCCwnOprDAO.qry4CustWarn("CUST_NUM1007A00000007");
		System.out.println(list);
	}
	public static void main1() {
		/* 指定环境，默认DEV */
		CommonUtils.setRunEnv(Env.DEV);

		String flag = "1007C";
		List<Map<String, Object>> list = new ArrayList<>();
		BusiClock clock = new BusiClock();
		for (int i = 0; i < 10; i++) {
			String indexFormat = String.format("%08d", i);
			Map<String, Object> map = new HashMap<>(); 
			map.put("RCRD_ID", UUID.randomUUID().toString());
			map.put("CUST_NUM", "CUST_NUM" +flag+ indexFormat);
			map.put("CUST_NAME", "CUST_NAME" +flag+ indexFormat);
			map.put("WARN_TIMES", i); 
			list.add(map);
		}
		OCCwnOprDAO.insert2CustWarn(list);
		System.out.println("bingo!");
		System.out.println("cost in second = " + clock.getCostOfSeconds());
		System.out.println("cost in milli = " + clock.getCostOfMillis());
	}
}
