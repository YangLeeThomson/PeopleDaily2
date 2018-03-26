package com.ronglian.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
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
	
    // 取得本地时间：
    private static Calendar cal = Calendar.getInstance();
    // 取得时间偏移量：
    private static int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
    // 取得夏令时差：
    private static int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
	
	//转换成utc时间格式
	public static long getUTCtime(Date date){
			cal.setTimeInMillis(date.getTime());
	        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
	        long mills = cal.getTimeInMillis();
//	        System.out.println("UTC = " + mills);
	        return mills;
	}
	
//	//入参millis为UTC时间
//    public void setUTCTime(long millis) {
//
//        cal.setTimeInMillis(millis);
//        SimpleDateFormat foo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time = foo.format(cal.getTime());
//        System.out.println("GMT time= " + time);
//
//        // 从本地时间里扣除这些差量，即可以取得UTC时间：
//        cal.add(java.util.Calendar.MILLISECOND, (zoneOffset + dstOffset));
//        time = foo.format(cal.getTime());
//        System.out.println("Local time = " + time);
//
//    }

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
		        }
		        //用Md5加密
	            String str = DigestUtils.md5DigestAsHex(temp.toString().getBytes("utf-8"));
	           //字符串转大写
	            sign = str.toUpperCase();
		        return sign;
		}
		
		//转换日期格式
		public static String changeDateTime(Date date){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
			return sdf.format(date);
		}
	}
