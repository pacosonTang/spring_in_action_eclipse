package com.swjtu.mybatis.dao.mybatis.cwn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.swjtu.mybatis.utils.DAOUtils;

/*接口*/
public class MYCwnOprDAO {
	private final static String namespace = "com.swjtu.mybatis.dao.EmployeeMapper";

	/**
	 * 插入客户预警次数
	 * @param list
	 */
	public static void insert2CustWarn(List<Map<String, Object>> list, String db) {
		Map<String, Object> params = new HashMap<>();
		params.put("LIST", list);
		DAOUtils.insert(namespace+".insert2CustWarn", params, db);
	}
}
