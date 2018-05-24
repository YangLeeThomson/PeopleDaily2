package com.ronglian.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ronglian.service.impl.APPManagerConfigServiceImpl;
import com.ronglian.utils.GetRequestJsonUtils;
import com.ronglian.utils.HttpRequestUtils;
import com.ronglian.utils.RongLianConstant;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.RongLianUtils;
import com.ronglian.utils.model.request.MyHttpServletRequestWrapper;

/**
 * @author liyang
 * 
 * @createTime 2017-12-21
 *
 */
@Component
public class SignInterceptor implements HandlerInterceptor{
	
	private static final String SALT = RongLianConstant.SALT;
	private static final long INTERVAL = RongLianConstant.INTERVAL;
	private static final String PASSURL = RongLianConstant.TOKEN_URI;
	private final Logger log = LoggerFactory.getLogger(getClass().getName());
	private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");
	@Resource
	private APPManagerConfigServiceImpl tokenService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取用户真实IP地址
		String ip = HttpRequestUtils.getIpAddress(request);
		 log.info("用户请求的Ip:"+ip,ip);
//		 System.out.println("用户请求的Ip:"+ip);
		//放行请求令牌tokenId的请求
		StringBuffer url = request.getRequestURL();
		if(url.toString().indexOf(PASSURL) != -1){
			return true;
		}
		
		//获取请求参数
		Map<String, Object> requestParams = new HashMap<String, Object>();
		String sign;
		String timeStamp; 
		String tokenId; 
		String jsonStr=new String();
		//GET
		if(request.getMethod().equalsIgnoreCase("GET")){
			 sign = request.getParameter("sign");
			 timeStamp = request.getParameter("timeStamp");
			 tokenId = request.getParameter("tokenId");
		}else{
			//POST
			MyHttpServletRequestWrapper myWrapper= new MyHttpServletRequestWrapper(request);  
            jsonStr = GetRequestJsonUtils.getRequestJsonString(myWrapper);  
            requestParams = GetRequestJsonUtils.parseObject(jsonStr);
//			requestParams = HttpRequestUtils.parseRequest(request);
			 sign = (String) requestParams.get("sign");
			Object obj = requestParams.get("timeStamp");
			 timeStamp = obj.toString();
			 tokenId = (String) requestParams.get("tokenId");
		}
		if(StringUtils.isBlank(sign)||StringUtils.isBlank(timeStamp)||StringUtils.isBlank(tokenId)){
			returnErrorMessage(response, "必须参数sign,timeStamp,tokenId不能为空！",103);
			return false;
		}
		
		
		/*
		 * 时间戳timeStamp校验，默认是30分钟
		 */
		long time = Long.parseLong(timeStamp);
		long currentTime = new Date().getTime()/1000;
		if(currentTime > (time+INTERVAL)){
			returnErrorMessage(response,"时间戳：timeStamp 超时。",104);
			return false;
		}
		
		
		/*
		 * 令牌tokenId的校验
		 */
	      if (tokenService == null) {
	          BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()); 
	          tokenService = (APPManagerConfigServiceImpl)factory.getBean("tokenService"); 
	       } 
		boolean flag = this.tokenService.getTokenBytokenId(tokenId);
		if(flag){
			//flag = true,说明redis中tokenId失效
			returnErrorMessage(response,"令牌：tokenId 失效。",105);
			return false;
		}
		
		
		Map<String, String> params = new HashMap<String, String>();
		if(request.getMethod().equals("GET")){
			//GET获取请求参数
	        Enumeration<?> pNames =  request.getParameterNames();
	        while (pNames.hasMoreElements()) {
	            String pName = (String) pNames.nextElement();
	            if("sign".equals(pName))continue;
	            String pValue = request.getParameter(pName);
	            params.put(pName, pValue);
	        }
		}else{
			//POST获取求参数
			Set<String> keys = requestParams.keySet();
			Iterator<String> it = keys.iterator();
			while(it.hasNext()){
				String key = it.next();
				if("sign".equals(key)||"data".equals(key))continue;
				params.put(key, requestParams.get(key).toString());
			}
//			System.out.println(jsonStr);
//			System.out.println(jsonStr.indexOf("data"));
//			System.out.println(jsonStr.lastIndexOf("}}"));
			
			//标准原生json在末尾，
			int index = jsonStr.lastIndexOf("}\r\n}");
			//index2表示在data在中间
			int index2 = jsonStr.lastIndexOf("},");
			//非原生json在末尾
			int index3 = jsonStr.lastIndexOf("}}");
			//data数据是数组或list
			int index4 = jsonStr.lastIndexOf("]}");
			int index5 = jsonStr.lastIndexOf("],");
			
			
			if(index3 > index){
				index = index3;
			}
			if(index4>index){
					index = index4;
			}
			if(index5>index){
				
				index = index5;
			}
			/*if(!(index3 > 0) && !(index4 > 0) && !(index5 > 0) && index2 > 0){
				index = index2;
			}*/
			if(index2>index){
				index=index2;
			}
			int start = jsonStr.indexOf("data");
			if(start > 0 && index > 0)
				params.put("data", jsonStr.substring(start+6, index+1));
		}
        
		//加入检验秘钥secretKey
		String secretKey = this.tokenService.getSecretKeyByToken(tokenId);
		params.put("secretKey", secretKey);
		
		
        /*
         * 生成签名对比
		 */
         String IsSign = RongLianUtils.createSign(params, SALT);
        if(IsSign != null && sign.equals(IsSign)){
        	return true;
        }else{
        	returnErrorMessage(response,"签名：sign 不符合，请按签名规则生成！",102);
        	return false;
        }
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private void returnErrorMessage(HttpServletResponse response, String errorMessage,int code) throws IOException  {
        RongLianResult rst = new RongLianResult();
        RongLianResult result = rst.build(code, errorMessage);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
//Get the printwriter object from response to write the required json object to the output stream
        PrintWriter out = response.getWriter();
//Assuming your json object is **jsonObject**, perform the following, it will return your json object
        ObjectMapper mapper = new ObjectMapper();
        String jsonOfRST =  mapper.writeValueAsString(result);
        out.print(jsonOfRST);
        out.flush();
    }

}
