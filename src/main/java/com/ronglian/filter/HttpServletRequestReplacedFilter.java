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
 *  ʹ��ע���ע������
 * @WebFilter��һ��ʵ����javax.servlet.Filter�ӿڵ��ඨ��Ϊ������
 * ����filterName����������������,��ѡ
 * ����urlPatternsָ��Ҫ���� ��URLģʽ,Ҳ��ʹ������value������.(ָ��Ҫ���˵�URLģʽ�Ǳ�ѡ����)
 * ����һ��ʵ��Filter���࣬��дdoFilter��������ServletRequest�滻Ϊ�Զ����myRequest�� 
 * @author liyang
 * @createTime 2018��1��24��
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
	        	//��ServletRequest�滻Ϊ�Զ����myRequest�� 
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
