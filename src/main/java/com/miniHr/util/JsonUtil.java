package com.miniHr.util;


import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * <pre>
 * any changes, please update below operation histories:
 * seq	updater		operation			time
 * 1	lz			create file			Feb 27, 2013
 * </pre>
 */
public class JsonUtil {

	private static final String DATE_FMT_YDS = "yyyy-MM-dd hh:mm:ss";
	/**
	 * JSON 引擎,不进行特殊字符转义
	 */
	private static Gson engine = new GsonBuilder().disableHtmlEscaping().setDateFormat(DATE_FMT_YDS).create();
	/**
	 * JSON 引擎
	 */
	private static Gson engine1 = new Gson();

	/**
	 * 反序列化JSON
	 * 
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> classOfT) {
		return engine.fromJson(json, classOfT);
	}

	/**
	 * 反序列化JSON
	 * 
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String json, Type type) {
		return engine.fromJson(json, type);
	}

	/**
	 * 序列化对象为JSON
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		return engine.toJson(obj);
	}

	/**
	 * 
	 * @param obj
	 * @param escaping
	 *            是否需要转义
	 * @return
	 */
	public static String toJson(Object obj, boolean escaping) {
		if (escaping) {
			return engine1.toJson(obj);
		} else {
			return toJson(obj);
		}

	}

//	public static void main(String args[]) {
//		System.out.println(toJson(null));
//		System.out.println(fromJson("null", Object.class));
//		Map<String, String> a = new HashMap<String, String>();
//		a.put("fd", "ab8&jfdk%jk$..>fd<.,jfkdjf==fkdjf");
//		System.out.println(toJson(a));
//		System.out.println(toJson(a, true));
//	}
}