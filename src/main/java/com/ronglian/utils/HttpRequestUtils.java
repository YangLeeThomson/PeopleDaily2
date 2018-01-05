/**
 * 
 */
package com.ronglian.utils;

/**
 * @author liyang
 * @createTime 2017��12��26��
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
 * ����POST�ύ��json���ݹ����� 
 */   
public class HttpRequestUtils {

	private static Logger log = Logger.getLogger(HttpRequestUtils.class);  
	  
    /** 
     * ����request�������� 
     * @param request 
     * @return �����json�ַ��� 
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
     * ����request��json���� 
     * @param request 
     * @return Map 
     */  
    public static synchronized Map<String, Object> parseRequest(  
            HttpServletRequest request) {  
        Map<String, Object> map = new HashMap<String, Object>() ;  
        try {  
            String req = getRequestBody(request);  
            if(! StringUtils.isBlank(req)){
            	// json��ʽ�ַ���jsonStringת��ΪJSONObject����
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
     * ��ȡmap��value 
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
