/**
 * 
 */
package com.ronglian.interceptor;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ronglian.jedis.JedisDao;
import com.ronglian.utils.HttpRequestUtils;
import com.ronglian.utils.RongLianConstant;

/**
 * @author liyang
 * @createTime 2018年1月18日
 */
@Component
public class LoginInterceptor implements HandlerInterceptor{

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Resource
	private JedisDao jedisDao;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String accessToken = null;
		
		if(request.getMethod().equalsIgnoreCase("GET")){
			accessToken = request.getParameter("accessToken");
			
		}
		if(request.getMethod().equalsIgnoreCase("POST")){
			Map<String,Object> requestParams = HttpRequestUtils.parseRequest(request);
			accessToken = (String) requestParams.get("accessToken");
			System.out.println("accessToken"+accessToken);
		}
		if(accessToken != null ){
			String json = jedisDao.get(accessToken);
			if(json != null){
				jedisDao.expire(accessToken, RongLianConstant.REDIS_ACCESS_TOKEN_EXPIRE);
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
