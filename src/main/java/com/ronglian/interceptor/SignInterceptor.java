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
//�ӿ���֤������
@Component	//springboot�ʹ�ͳspringMVC��ͬ(���������ע���ע��ʹ�ã�һ��ȱʧ��service���޷�ע��)�ӣ�����
public class SignInterceptor implements HandlerInterceptor{
	
	private static final String SALT = RongLianConstant.SALT;
	private static final long INTERVAL = RongLianConstant.INTERVAL;
	private static final String PASSURL = RongLianConstant.TOKEN_URI;
	
	@Resource
	private APPManagerConfigServiceImpl tokenService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		
		//1�����л�ȡ����tokenId������
		StringBuffer url = request.getRequestURL();
		if(url.toString().indexOf(PASSURL) != -1){
			return true;
		}
		
		
		//�ж��Ƿ����timestamp��tokenId��sign���������ȱʧ�����򷵻�false
		Map<String, Object> requestParams = new HashMap<String, Object>();
		String sign;
		String timeStamp; 
		String tokenId; 
		String jsonStr=new String();
		//GET����
		if(request.getMethod().equalsIgnoreCase("GET")){
			 sign = request.getParameter("sign");
			 timeStamp = request.getParameter("timeStamp");
			 tokenId = request.getParameter("tokenId");
		}else{
			//POST����ʽ��
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
			return false;
		}
		
		
		/*
		 * �жϷ������ӵ������ʱ��Ͳ����е�ʱ����Ƿ����ܳ�һ��ʱ�䣨ʱ���Զ�������Сʱ����
		 * ���������˵����url�Ѿ����ڣ����url�����ã����ı���ʱ��������ǻᵼ��signǩ������ȣ�
		 */
		long time = Long.parseLong(timeStamp);
		long currentTime = new Date().getTime()/1000;
		if(currentTime > (time+INTERVAL)){
			return false;
		}
		
		
		/*
		 * �ж�tokenId(����)�Ƿ���Ч
		 * �������������tokenId�����tokenId�Ƿ���Ч���Ƿ���ڡ�
		 */
	      if (tokenService == null) {//���serviceΪnull�޷�ע������ 
	          BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()); 
	          tokenService = (APPManagerConfigServiceImpl)factory.getBean("tokenService"); 
	       } 
		boolean flag = this.tokenService.getTokenBytokenId(tokenId);
		if(flag){
			//flag = true,˵��redis������û��tokenId����
			return false;
		}
		
		
		Map<String, String> params = new HashMap<String, String>();
		if(request.getMethod().equals("GET")){
			//GET��ȡȫ������ɲ���
	        Enumeration<?> pNames =  request.getParameterNames();
	        while (pNames.hasMoreElements()) {
	            String pName = (String) pNames.nextElement();
	            if("sign".equals(pName))continue;
	            String pValue = request.getParameter(pName);
	            params.put(pName, pValue);
	        }
		}else{
			//POST��ȡȫ������ɲ���
			Set<String> keys = requestParams.keySet();
			Iterator<String> it = keys.iterator();
			while(it.hasNext()){
				String key = it.next();
				if("sign".equals(key)||"data".equals(key))continue;
				params.put(key, requestParams.get(key).toString());
			}
			if(jsonStr.indexOf("data")>0&&jsonStr.lastIndexOf("}}")>0)
				params.put("data", jsonStr.substring(jsonStr.indexOf("data")+6, jsonStr.lastIndexOf("}}")+1));
		}
        
		//�ڼ��ܲ����м���secretKey
		String secretKey = this.tokenService.getSecretKeyByToken(tokenId);
		params.put("secretKey", secretKey);
		
		
        /*
		 * ���ݿͻ��������url�������������˰���ͬ���Ĺ�������signǩ��������sign���Ƿ���ȡ�
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
