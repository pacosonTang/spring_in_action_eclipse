package com.swjtu.crud.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionUtils {
	
	public static <K, V> void printListMap(List<Map<K, V>> list) {
		
		for(Map<K, V> map : list) {
			System.out.println("==============================");
			Set<Map.Entry<K, V>> entries = map.entrySet();
			for (Map.Entry<K, V> entry : entries) {
				System.out.println("key = " + entry.getKey() + ", value=" + entry.getValue());
			}
		}
	}
}
