package com.ronglian.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ronglian.interceptor.LoginInterceptor;
import com.ronglian.interceptor.SignInterceptor;
/**
 * @author liyang
 * 
 * @createTime 2017-12-21
 * ������������config��
 */
@EnableWebMvc
@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    // ������������һ����������
        // addPathPatterns ����������ع���
        // excludePathPatterns �ų�����(���л�ȡ���Ƶ�����)
//	       registry.addInterceptor(new SignInterceptor())
//	       .addPathPatterns("/**").excludePathPatterns("/oauth/*");
		
		   registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
	       registry.addInterceptor(new SignInterceptor()).addPathPatterns("/**");
	       super.addInterceptors(registry);
	}
	
	
    //�ؼ�������������Ϊbeanд��������(���@compentʹ�ã�һ��ȱ�����service���޷�ע��������������)��
	//springboot�ʹ�ͳspringMVC��ͬ
    @Bean
    public SignInterceptor signInterceptor(){
        return new SignInterceptor();
    }
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }
}
