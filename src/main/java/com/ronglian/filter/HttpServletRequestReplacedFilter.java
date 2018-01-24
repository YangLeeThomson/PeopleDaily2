/**
 * 
 */
package com.ronglian.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.ronglian.utils.model.request.MyHttpServletRequestWrapper;

/**
 *  使用注解标注过滤器
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器
 * 属性filterName声明过滤器的名称,可选
 * 属性urlPatterns指定要过滤 的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 * 创建一个实现Filter的类，重写doFilter方法，将ServletRequest替换为自定义的myRequest类 
 * @author liyang
 * @createTime 2018年1月24日
 */
@WebFilter(filterName="myFilter",urlPatterns="/*")
public class HttpServletRequestReplacedFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		   ServletRequest requestWrapper = null;  
	        if(request instanceof HttpServletRequest) {  
	            requestWrapper = new MyHttpServletRequestWrapper((HttpServletRequest) request);  
	        }  
	        if(requestWrapper == null) {  
	            chain.doFilter(request, response);  
	        } else {  
	        	//将ServletRequest替换为自定义的myRequest类 
	            chain.doFilter(requestWrapper, response);  
	        }     
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
