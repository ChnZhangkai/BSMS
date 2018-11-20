package com.bsms.web.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.bsms.web.vo.HTTPResut;

/**
 * @ClassName: HTTPResultUtils.java
 * @Description: Http请求结果处理工具类
 * @author: 张凯
 * @Date: 2018年7月14日 下午6:24:39
 * @parma <T>
 */
public class HTTPResultUtils {
	
	static ConcurrentMap<Type, Type> classTypeCache = new ConcurrentHashMap<Type, Type>(16,0.75f,1);
	static ConcurrentMap<Type, Type> classListTypeCache = new ConcurrentHashMap<Type, Type>(16,0.75f,1);
	static ConcurrentMap<Type, Type> classSetTypeCache = new ConcurrentHashMap<Type, Type>(16,0.75f,1);
	
	public static <T> HTTPResut<T> parseObject(String jsonString,Class<T> clazz) {
		Type type = classTypeCache.get(clazz);
		if (type == null) {
			type = new ParameterizedTypeImpl(new Type[] { clazz }, null, HTTPResut.class);
			classTypeCache.putIfAbsent(clazz, type);
			type = classTypeCache.get(clazz);
		}
		return JSON.parseObject(jsonString,type);
	}
	
	public static <T> HTTPResut<List<T>> parseArray(String jsonString,Class<T> clazz) {
		Type typeOfList = classListTypeCache.get(clazz);
		if (typeOfList == null) {
			typeOfList = new ParameterizedTypeImpl(new Type[] { clazz }, null, List.class);
			classListTypeCache.putIfAbsent(clazz, typeOfList);
			typeOfList = classListTypeCache.get(clazz);
		}
		Type totalType = classListTypeCache.get(typeOfList);
		if (totalType == null) {
			totalType = new ParameterizedTypeImpl(new Type[] { clazz }, null, HTTPResut.class);
			classListTypeCache.putIfAbsent(typeOfList, totalType);
			totalType = classListTypeCache.get(typeOfList);
		}
		return JSON.parseObject(jsonString,totalType);
	}
	
	public static <T> HTTPResut<Set<T>> parseSet(String jsonString,Class<T> clazz) {
		Type typeOfSet = classSetTypeCache.get(clazz);
		if (typeOfSet == null) {
			typeOfSet = new ParameterizedTypeImpl(new Type[] { clazz }, null, List.class);
			classSetTypeCache.putIfAbsent(clazz, typeOfSet);
			typeOfSet = classSetTypeCache.get(clazz);
		}
		Type totalType = classSetTypeCache.get(typeOfSet);
		if (totalType == null) {
			totalType = new ParameterizedTypeImpl(new Type[] { clazz }, null, HTTPResut.class);
			classSetTypeCache.putIfAbsent(typeOfSet, totalType);
			totalType = classSetTypeCache.get(typeOfSet);
		}
		return JSON.parseObject(jsonString,totalType);
	}
	
	public static <K,V> HTTPResut<Map<K, V>> parseMap(String jsonString,Class<K> keyClazz,Class<V> valueClazz) {
		Type typeOfSet = new ParameterizedTypeImpl(new Type[] { keyClazz,valueClazz }, null, Map.class);
		Type totalType = classSetTypeCache.get(typeOfSet);
		if (totalType == null) {
			totalType = new ParameterizedTypeImpl(new Type[] { typeOfSet }, null, HTTPResut.class);
			classSetTypeCache.putIfAbsent(typeOfSet, totalType);
			totalType = classSetTypeCache.get(typeOfSet);
		}
		return JSON.parseObject(jsonString,totalType);
	}
	
}
