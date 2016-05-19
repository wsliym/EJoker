package com.jiefzz.ejoker.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ParameterizedTypeUtil {

	/**
	 * 判断是否是可直接序列化类型
	 * @param clazz
	 * @return boolean
	 */
	public static boolean isDirectSerializableType(Class<?> clazz) {
		return (clazz.isPrimitive()||acceptTypeSet.contains(clazz))?true:false;
	}
	/**
	 * 判断是否是可直接序列化类型
	 * @see isDirectSerializableType(Class<?> clazz)
	 * @param obj
	 * @return
	 */
	public static boolean isDirectSerializableType(Object obj) {
		return isDirectSerializableType(obj.getClass());
	}

	/**
	 * 确认是否是java的集合类型
	 * @param clazz
	 * @return boolean
	 */
	public static boolean hasSublevel(Class<?> clazz) {

		for (Class<?> specialTypeBase : specialTypeSet) {
			if ( specialTypeBase.isAssignableFrom(clazz) )
				return true;
		}
		return false;
	}
	
	/**
	 * 确认是否是java的集合类型
	 * @see hasSublevel(Class<?> clazz)
	 * @param clazz
	 * @return boolean
	 */
	public static boolean hasSublevel(Object object) {
		return hasSublevel(object.getClass());
	}
	
	/**
	 * 确认是可序列化的一位数组
	 * @param clazz
	 * @return
	 */
	public static boolean isAcceptArray(Class<?> clazz){
		return clazz.isArray() && isDirectSerializableType(clazz.getComponentType());
	}

	/**
	 * 确认是可序列化的一位数组
	 * @param object
	 * @return
	 */
	public static boolean isAcceptArray(Object object){
		return isAcceptArray(object.getClass());
	}
	
	public static Object[] arrayTypeAsObject(Object object) {
		if(!object.getClass().isArray()) return null;
		Class<?> clazz = object.getClass().getComponentType();
		//long
		if(long.class==clazz){
			int arraySyze = ((long[] )object).length;
			Long[] cArray = new Long[arraySyze];
			for(int i=0; i<arraySyze; i++)
				cArray[i] = ((long[] )object)[i];
			return cArray;
		}
		//int
		if(int.class==clazz){
			int arraySyze = ((int[] )object).length;
			Integer[] cArray = new Integer[arraySyze];
			for(int i=0; i<arraySyze; i++)
				cArray[i] = ((int[] )object)[i];
			return cArray;
		}
		//short
		if(short.class==clazz){
			int arraySyze = ((short[] )object).length;
			Short[] cArray = new Short[arraySyze];
			for(int i=0; i<arraySyze; i++)
				cArray[i] = ((short[] )object)[i];
			return cArray;
		}
		//double
		if(double.class==clazz){
			int arraySyze = ((double[] )object).length;
			Double[] cArray = new Double[arraySyze];
			for(int i=0; i<arraySyze; i++)
				cArray[i] = ((double[] )object)[i];
			return cArray;
		}
		//float
		if(float.class==clazz){
			int arraySyze = ((float[] )object).length;
			Float[] cArray = new Float[arraySyze];
			for(int i=0; i<arraySyze; i++)
				cArray[i] = ((float[] )object)[i];
			return cArray;
		}
		//char
		if(long.class==clazz){
			int arraySyze = ((char[] )object).length;
			Character[] cArray = new Character[arraySyze];
			for(int i=0; i<arraySyze; i++)
				cArray[i] = ((char[] )object)[i];
			return cArray;
		}
		//byte
		if(byte.class==clazz){
			int arraySyze = ((byte[] )object).length;
			Byte[] cArray = new Byte[arraySyze];
			for(int i=0; i<arraySyze; i++)
				cArray[i] = ((byte[] )object)[i];
			return cArray;
		}
		//boolean
		if(boolean.class==clazz){
			int arraySyze = ((boolean[] )object).length;
			Boolean[] cArray = new Boolean[arraySyze];
			for(int i=0; i<arraySyze; i++)
				cArray[i] = ((boolean[] )object)[i];
			return cArray;
		}
		return (Object[] )object;
	}

	/**
	 * 获取基础数据的对象类型
	 * @param clazz
	 * @return
	 */
	public static Class<?> getPrimitiveObjectType(Class<?> clazz){
		return clazz.isPrimitive()?primitiveTypeMap.get(clazz):clazz;
	}
	
	private static final Set<Class<?>> acceptTypeSet = new HashSet<Class<?>>(Arrays.asList(new Class<?>[]{
		Boolean.class, Byte.class, Character.class, Short.class, Integer.class, Long.class, Float.class, Double.class, String.class
	}));
	private static final Set<Class<?>> specialTypeSet = new HashSet<Class<?>>(Arrays.asList(new Class<?>[]{
		Collection.class, Map.class
	}));
	
	private static final Map<Class<?>,Class<?>> primitiveTypeMap = new HashMap<Class<?>,Class<?>>();
	
	static {
		primitiveTypeMap.put(int.class, Integer.class);
		primitiveTypeMap.put(long.class, Long.class);
		primitiveTypeMap.put(short.class, Short.class);
		primitiveTypeMap.put(double.class, Double.class);
		primitiveTypeMap.put(float.class, Float.class);
		primitiveTypeMap.put(byte.class, Byte.class);
		primitiveTypeMap.put(char.class, Character.class);
		primitiveTypeMap.put(boolean.class, Boolean.class);
		primitiveTypeMap.put(void.class, Void.class);
	}
}
