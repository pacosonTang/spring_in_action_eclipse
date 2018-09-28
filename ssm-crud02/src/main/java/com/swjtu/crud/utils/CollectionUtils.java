package com.swjtu.crud.utils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class CollectionUtils {
	
	public static void print(Collection<Map<String, Object>> collection) {
		
		System.out.print("map={");
		for (Map<String, Object> map : collection) {
			Set<Map.Entry<String, Object>> entries =  map.entrySet();
			for (Map.Entry<String, Object> entry :entries) {
				System.out.print(entry.getKey() + "=" + entry.getValue() + ", ");
			}
		}
		System.out.println("}");
	}
}
