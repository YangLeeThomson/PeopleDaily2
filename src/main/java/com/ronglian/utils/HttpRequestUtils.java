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
     * @param request 
     * @return Map 
     */  
    public static synchronized Map<String, Object> parseRequest(  
            HttpServletRequest request) {  
        Map<String, Object> map = new HashMap<String, Object>() ;  
        try {  
            String req = getRequestBody(request);  
            if(! StringUtils.isBlank(req)){
            	// json格式字符串jsonString转化为JSONObject对象
            	Map jsonObject = JSON.parseObject(req);
            	Set<String> keys = new HashSet<String>();
            	keys = jsonObject.keySet();
            	for(String key:keys){
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
     * @param map 
     * @param key 
     * @return  
     */  
    public static Object getRequestBodyValue(  
            Map<String, Object> map, String key) {  
        if (map != null && (!StringUtils.isBlank(key)) && map.containsKey(key)) {  
            return map.get(key);  
        }  
        return null;  
    }  
}
