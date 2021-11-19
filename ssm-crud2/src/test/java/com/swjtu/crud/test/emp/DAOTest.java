package com.swjtu.crud.test.emp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.swjtu.crud.utils.CollectionUtils;
import com.swjtu.crud.utils.DAOUtils;

public class DAOTest {
	private final static String NAME_SPACE = "com.swjtu.crud.dao.dept";
	
	@Test
	public void testSelectList() {
		
		String method = "getDeptList";
		
		Map<String, Object> param = new HashMap<>();
		param.put("LIST", Arrays.asList("1", "2", "3"));
		List<Map<String, Object>> list = DAOUtils.selectList(NAME_SPACE + "." + method, param);

		/*打印*/
		CollectionUtils.printListMap(list);
	}
}
