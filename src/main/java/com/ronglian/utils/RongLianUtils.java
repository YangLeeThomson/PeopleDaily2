package com.ronglian.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.util.DigestUtils;

/**
 * @author liyang
 * @createTime 2017-12-20
 * */
public class RongLianUtils {

	//生成签名sign
	//参数salt为荣联和微软约定字符串：ronglian
	public static String createSign(Map<String, String> params, String salt)
	            throws UnsupportedEncodingException {
		    String sign = null;
			//加点盐salt
			params.put("salt", salt);
			Set<String> keysSet = params.keySet();
	        Object[] keys = keysSet.toArray();
	        //参数排序
	        Arrays.sort(keys);
	        StringBuffer temp = new StringBuffer();
	        boolean first = true;
	        for (Object key : keys) {
	            if (first) {
	                first = false;
	            } else {
	                temp.append("&");
	            }
	            temp.append(key).append("=");
	            Object value = params.get(key);
	            String valueString = "";
	            if (null != value) {
	                valueString = String.valueOf(value);
	            }
	            temp.append(valueString);
	           //用Md5加密
	            String str = DigestUtils.md5DigestAsHex(temp.toString().getBytes("utf-8"));
	           //字符串转大写
	            sign = str.toUpperCase();
	        }
	        return sign;
	}
	
	//转换日期格式
	public static String changeDateTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEE HH:mm:ss");
		return sdf.format(date);
	}
}
