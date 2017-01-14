package com.hoperun.rdc.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * jsonHelper google.gson implement version
 * 
 * @ClassName: JsonHelper.java
 * @Description:
 * @author yin_changbao
 * @Date: May 20, 2016 10:26:03 AM
 *
 */
public final class JsonHelper {

	private static final Logger logger = LoggerFactory.getLogger(JsonHelper.class);

	// singleton only fits for those requests which have no adapter
	// informations, that is mostly requested
	private static Gson gsonWithNoAdapter = new Gson();

	private JsonHelper() {
	}

	/**
	 * 
	 * transform json string to object, supports Collection<T> model
	 * 
	 * @param json
	 * @param typeOfT
	 *            type of expected output object, cloud be Collection<E> or map
	 *            <XX,XX>
	 * @return
	 */
	public static <T> T json2Bean(String json, java.lang.reflect.Type typeOfT) {
		return json2Bean(json, typeOfT, null);
	}

	/**
	 * @param json
	 *            input string
	 * @param classOfT
	 *            class of expected output object
	 * @return
	 */
	public static <T> T json2Bean(String json, Class<T> classOfT) {
		return json2Bean(json, classOfT, null);
	}

	/**
	 * transform json string to object, supports Collection<T> model
	 * 
	 * @param json
	 * @param typeOfT
	 *            type of expected output object, cloud be Collection<E> or map
	 *            <XX,XX>
	 * @param adapters
	 *            adapters
	 * @return
	 */
	public static <T> T json2Bean(String json, java.lang.reflect.Type typeOfT, Map<Class<?>, Object> adapters) {
		Gson gson = getGson(adapters);
		return gson.fromJson(json, typeOfT);
	}

	/**
	 * @param json
	 *            input string
	 * @param classOfT
	 *            class of expected output object
	 * @param adapters
	 * @return
	 */
	public static <T> T json2Bean(String json, Class<T> classOfT, Map<Class<?>, Object> adapters) {
		Gson gson = getGson(adapters);
		return gson.fromJson(json, classOfT);
	}

	/**
	 * convert bean to json string, use default format adapter
	 * 
	 * @param jsonElement
	 * @return
	 */
	public static String bean2Json(Object jsonElement) {
		return bean2Json(jsonElement, null);
	}

	/**
	 * convert bean to json string, use given format adapter
	 * 
	 * @param jsonElement
	 * @param adapters
	 * @return
	 */
	public static String bean2Json(Object jsonElement, Map<Class<?>, Object> adapters) {
		Gson gson = getGson(adapters);
		return gson.toJson(jsonElement);
	}

	private static Gson getGson(Map<Class<?>, Object> adapters) {
		Gson gson = null;
		if (adapters != null) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			for (Map.Entry<Class<?>, Object> entry : adapters.entrySet())
				gsonBuilder.registerTypeAdapter(entry.getKey(), entry.getValue());
			gson = gsonBuilder.create();
		} else
			gson = gsonWithNoAdapter;
		return gson;
	}

}
