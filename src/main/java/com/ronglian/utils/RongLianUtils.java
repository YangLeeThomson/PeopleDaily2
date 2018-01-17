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

	//����ǩ��sign
	//����saltΪ������΢��Լ���ַ�����ronglian
	public static String createSign(Map<String, String> params, String salt)
	            throws UnsupportedEncodingException {
		    String sign = null;
			//�ӵ���salt
			params.put("salt", salt);
			Set<String> keysSet = params.keySet();
	        Object[] keys = keysSet.toArray();
	        //��������
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
	           //��Md5����
	            String str = DigestUtils.md5DigestAsHex(temp.toString().getBytes("utf-8"));
	           //�ַ���ת��д
	            sign = str.toUpperCase();
	        }
	        return sign;
	}
	
	//ת�����ڸ�ʽ
	public static String changeDateTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEE HH:mm:ss");
		return sdf.format(date);
	}
}
