/**
 * 
 */
package com.ronglian.utils;

/**
 * @author liyang
 * @createTime 2017年12月26日
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/* 
 * 解析POST提交的json数据工具类 
 */
public class HttpRequestUtils {

	private static Logger log = Logger.getLogger(HttpRequestUtils.class);

	/**
	 * 解析request的输入流
	 * 
	 * @param request
	 * @return 请求的json字符串
	 */
	public static synchronized String getRequestBody(HttpServletRequest request) {
		String str = null;
		try {
			str = IOUtils.toString(request.getInputStream());
		} catch (IOException e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 解析request的json数据
	 * 
	 * @param request
	 * @return Map
	 */
	public static synchronized Map<String, Object> parseRequest(
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String req = getRequestBody(request);
			if (!StringUtils.isBlank(req)) {
				// json��ʽ�ַ���jsonStringת��ΪJSONObject����
				Map jsonObject = JSON.parseObject(req);
				Set<String> keys = new HashSet<String>();
				keys = jsonObject.keySet();
				for (String key : keys) {
					map.put(key, jsonObject.get(key));
				}
			}
			log.info(map);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return map;
	}

	/**
	 * 获取map的value
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static Object getRequestBodyValue(Map<String, Object> map, String key) {
		if (map != null && (!StringUtils.isBlank(key)) && map.containsKey(key)) {
			return map.get(key);
		}
		return null;
	}

	/**
	 * 自定义访问对象工具类
	 * 
	 * 获取对象的IP地址等信息
	 * 
	 * @author yanglee
	 * 
	 *         获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 *         可是
	 *         ，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 *         答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 
	 *         如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
	 *         192.168.1.100
	 * 
	 *         用户真实IP为： 192.168.1.110
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
