package com.jiefzz.ejoker.z.common.service;

public interface IJSONConverter {

	public <T> String convert(T object);
	
	public <T> T revert(String jsonString, Class<T> clazz);
	
}
