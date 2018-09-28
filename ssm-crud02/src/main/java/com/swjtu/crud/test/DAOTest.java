package com.swjtu.crud.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.swjtu.crud.utils.CollectionUtils;
import com.swjtu.crud.utils.TR;


public class DAOTest {
	
	@Test
	public void test1() {
		Map<String, Object> params = new HashMap<>();
		List<Map<String, Object>> list = TR.DAO.selectList("com.swjtu.crud.dao.employee.empInfoQry", params);
		
		CollectionUtils.print(list);
	}
}
