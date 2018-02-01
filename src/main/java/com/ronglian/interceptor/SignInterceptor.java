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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ronglian.service.APPManagerConfigService;
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
//锟接匡拷锟斤拷证锟斤拷锟斤拷锟斤拷
@Component	//springboot锟酵达拷统springMVC锟斤拷同(锟斤拷锟斤拷锟斤拷锟斤拷锟阶拷锟斤拷注锟斤拷使锟矫ｏ拷一锟斤拷缺失锟斤拷service锟斤拷锟睫凤拷注锟斤拷)锟接ｏ拷锟斤拷锟斤拷
public class SignInterceptor implements HandlerInterceptor{
	
	private static final String SALT = RongLianConstant.SALT;
	private static final long INTERVAL = RongLianConstant.INTERVAL;
	private static final String PASSURL = RongLianConstant.TOKEN_URI;
	
	@Resource
	private APPManagerConfigServiceImpl tokenService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		
		//1锟斤拷锟斤拷锟叫伙拷取锟斤拷锟斤拷tokenId锟斤拷锟斤拷锟斤拷
		StringBuffer url = request.getRequestURL();
		if(url.toString().indexOf(PASSURL) != -1){
			return true;
		}
		
		
		//锟叫讹拷锟角凤拷锟斤拷锟絫imestamp锟斤拷tokenId锟斤拷sign锟斤拷锟斤拷锟斤拷锟斤拷锟饺笔э拷锟斤拷锟斤拷蚍祷锟絝alse
		Map<String, Object> requestParams = new HashMap<String, Object>();
		String sign;
		String timeStamp; 
		String tokenId; 
		String jsonStr=new String();
		//GET锟斤拷锟斤拷
		if(request.getMethod().equalsIgnoreCase("GET")){
			 sign = request.getParameter("sign");
			 timeStamp = request.getParameter("timeStamp");
			 tokenId = request.getParameter("tokenId");
		}else{
			//POST锟斤拷锟斤拷式锟斤拷
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
		 * 锟叫断凤拷锟斤拷锟斤拷锟接碉拷锟斤拷锟斤拷锟绞憋拷锟酵诧拷锟斤拷锟叫碉拷时锟斤拷锟斤拷欠锟斤拷锟斤拷艹锟揭伙拷锟绞憋拷洌ㄊ憋拷锟斤拷远锟斤拷锟斤拷锟斤拷锟叫∈憋拷锟斤拷锟�
		 * 锟斤拷锟斤拷锟斤拷锟斤拷锟剿碉拷锟斤拷锟絬rl锟窖撅拷锟斤拷锟节ｏ拷锟斤拷锟絬rl锟斤拷锟斤拷锟矫ｏ拷锟斤拷锟侥憋拷锟斤拷时锟斤拷锟斤拷锟斤拷锟斤拷腔岬硷拷锟絪ign签锟斤拷锟斤拷锟斤拷龋锟�
		 */
		long time = Long.parseLong(timeStamp);
		long currentTime = new Date().getTime()/1000;
		if(currentTime > (time+INTERVAL)){
			returnErrorMessage(response,"时间戳：timeStamp 超时。",104);
			return false;
		}
		
		
		/*
		 * 锟叫讹拷tokenId(锟斤拷锟斤拷)锟角凤拷锟斤拷效
		 * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟絫okenId锟斤拷锟斤拷锟絫okenId锟角凤拷锟斤拷效锟斤拷锟角凤拷锟斤拷凇锟�
		 */
	      if (tokenService == null) {//锟斤拷锟絪ervice为null锟睫凤拷注锟斤拷锟斤拷锟斤拷 
	          BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()); 
	          tokenService = (APPManagerConfigServiceImpl)factory.getBean("tokenService"); 
	       } 
		boolean flag = this.tokenService.getTokenBytokenId(tokenId);
		if(flag){
			//flag = true,说锟斤拷redis锟斤拷锟斤拷锟斤拷没锟斤拷tokenId锟斤拷锟斤拷
			returnErrorMessage(response,"令牌：tokenId 失效。",105);
			return false;
		}
		
		
		Map<String, String> params = new HashMap<String, String>();
		if(request.getMethod().equals("GET")){
			//GET锟斤拷取全锟斤拷锟斤拷锟斤拷刹锟斤拷锟�
	        Enumeration<?> pNames =  request.getParameterNames();
	        while (pNames.hasMoreElements()) {
	            String pName = (String) pNames.nextElement();
	            if("sign".equals(pName))continue;
	            String pValue = request.getParameter(pName);
	            params.put(pName, pValue);
	        }
		}else{
			//POST锟斤拷取全锟斤拷锟斤拷锟斤拷刹锟斤拷锟�
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
        
		//锟节硷拷锟杰诧拷锟斤拷锟叫硷拷锟斤拷secretKey
		String secretKey = this.tokenService.getSecretKeyByToken(tokenId);
		params.put("secretKey", secretKey);
		
		
        /*
		 * 锟斤拷锟捷客伙拷锟斤拷锟斤拷锟斤拷锟絬rl锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟剿帮拷锟斤拷同锟斤拷锟侥癸拷锟斤拷锟斤拷锟斤拷sign签锟斤拷锟斤拷锟斤拷锟斤拷sign锟斤拷锟角凤拷锟斤拷取锟�
		 */
        String IsSign = RongLianUtils.createSign(params, SALT);
        if(IsSign != null && sign.equals(IsSign)){
        	return true;
        }else{
        	returnErrorMessage(response,"签名：sign 不符合，请安签名规则生成！",102);
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
