package com.swjtu.jdbc.bean;

import java.lang.reflect.ParameterizedType;

public class BaseHibernateEntityDao<T>  {
	private Class<T> entityClass;

	public BaseHibernateEntityDao() {
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
