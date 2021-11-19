package com.swjtu.mybatis.dao.oscasedb.cwn;

import com.swjtu.mybatis.utils.DAOUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*接口*/
public class OCCwnOprDAO {
	private final static String namespace = "com.swjtu.mybatis.dao.oscasedb.cwn.occwnopr";
	private final static String dbname = "oscasedb";

	/**
	 * 插入客户预警次数
	 * @param list
	 */
	public static void insert2CustWarn(List<Map<String, Object>> list) {
		Map<String, Object> params = new HashMap<>();
		params.put("LIST", list);
		DAOUtils.insert(namespace+".insert2CustWarn", params, dbname);
	}

	/**
	 * 插入客户预警次数
	 * @param custNum
	 */
	public static List<Map<String, Object>> qry4CustWarn(String custNum) {
		Map<String, Object> params = new HashMap<>();
		params.put("CUST_NUM", custNum);
		return DAOUtils.selectList(namespace+".qry4CustWarn", params, dbname);
	}
}
