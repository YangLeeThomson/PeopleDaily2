package com.ronglian.interceptor;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ronglian.service.APPManagerConfigService;
import com.ronglian.service.impl.APPManagerConfigServiceImpl;
import com.ronglian.utils.GetRequestJsonUtils;
import com.ronglian.utils.HttpRequestUtils;
import com.ronglian.utils.RongLianConstant;
import com.ronglian.utils.RongLianUtils;
import com.ronglian.utils.model.request.MyHttpServletRequestWrapper;

/**
 * @author liyang
 * 
 * @createTime 2017-12-21
 *
 */
//接口认证拦截器
@Component	//springboot和传统springMVC不同(配合拦截器注册和注册使用，一旦缺失，service层无法注入)坑！！！
public class SignInterceptor implements HandlerInterceptor{
	
	private static final String SALT = RongLianConstant.SALT;
	private static final long INTERVAL = RongLianConstant.INTERVAL;
	private static final String PASSURL = RongLianConstant.TOKEN_URI;
	
	@Resource
	private APPManagerConfigServiceImpl tokenService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		
		//1、放行获取令牌tokenId的请求
		StringBuffer url = request.getRequestURL();
		if(url.toString().indexOf(PASSURL) != -1){
			return true;
		}
		
		
		//判断是否包含timestamp，tokenId，sign参数，如果缺失参数则返回false
		Map<String, Object> requestParams = new HashMap<String, Object>();
		String sign;
		String timeStamp; 
		String tokenId; 
		//GET请求
		if(request.getMethod().equalsIgnoreCase("GET")){
			 sign = request.getParameter("sign");
			 timeStamp = request.getParameter("timeStamp");
			 tokenId = request.getParameter("tokenId");
		}else{
			//POST请求方式下
			MyHttpServletRequestWrapper myWrapper= new MyHttpServletRequestWrapper(request);  
            String jsonStr = GetRequestJsonUtils.getRequestJsonString(myWrapper);  
            requestParams = GetRequestJsonUtils.parseObject(jsonStr);
//			requestParams = HttpRequestUtils.parseRequest(request);
			 sign = (String) requestParams.get("sign");
			Object obj = requestParams.get("timeStamp");
			 timeStamp = obj.toString();
			 tokenId = (String) requestParams.get("tokenId");
		}
		if(StringUtils.isBlank(sign)||StringUtils.isBlank(timeStamp)||StringUtils.isBlank(tokenId)){
			return false;
		}
		
		
		/*
		 * 判断服务器接到请求的时间和参数中的时间戳是否相差很长一段时间（时间自定义如半个小时），
		 * 如果超过则说明该url已经过期（如果url被盗用，他改变了时间戳，但是会导致sign签名不相等）
		 */
		long time = Long.parseLong(timeStamp);
		long currentTime = new Date().getTime()/1000;
		if(currentTime > (time+INTERVAL)){
			return false;
		}
		
		
		/*
		 * 判断tokenId(令牌)是否有效
		 * 根据请求过来的tokenId，检查tokenId是否有效及是否过期。
		 */
	      if (tokenService == null) {//解决service为null无法注入问题 
	          BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()); 
	          tokenService = (APPManagerConfigServiceImpl)factory.getBean("tokenService"); 
	       } 
		boolean flag = this.tokenService.getTokenBytokenId(tokenId);
		if(flag){
			//flag = true,说明redis缓存中没有tokenId数据
			return false;
		}
		
		
		Map<String, String> params = new HashMap<String, String>();
		if(request.getMethod().equals("GET")){
			//GET获取全部请求成参数
	        Enumeration<?> pNames =  request.getParameterNames();
	        while (pNames.hasMoreElements()) {
	            String pName = (String) pNames.nextElement();
	            if("sign".equals(pName))continue;
	            String pValue = request.getParameter(pName);
	            params.put(pName, pValue);
	        }
		}else{
			//POST获取全部请求成参数
			Set<String> keys = requestParams.keySet();
			Iterator<String> it = keys.iterator();
			while(it.hasNext()){
				String key = it.next();
				if("sign".equals(key))continue;
				params.put(key, requestParams.get(key).toString());
			}
		}
        
		//在加密参数中加入secretKey
		String secretKey = this.tokenService.getSecretKeyByToken(tokenId);
		params.put("secretKey", secretKey);
		
		
        /*
		 * 根据客户端请求的url参数，服务器端按照同样的规则生成sign签名与请求sign看是否相等。
		 */
        String IsSign = RongLianUtils.createSign(params, SALT);
        if(IsSign != null && sign.equals(IsSign)){
        	return true;
        }else{
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

}
