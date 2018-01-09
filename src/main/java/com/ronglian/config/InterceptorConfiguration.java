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
 * 拦截器的配置config类
 */
@EnableWebMvc
@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 排除拦截(放行获取令牌的请求)
//	       registry.addInterceptor(new SignInterceptor())
//	       .addPathPatterns("/**").excludePathPatterns("/oauth/*");
		
		   registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
	       registry.addInterceptor(new SignInterceptor()).addPathPatterns("/**");
	       super.addInterceptors(registry);
	}
	
	
    //关键，将拦截器作为bean写入配置中(配合@compent使用，一旦缺少这个service层无法注入拦截器！！！)坑
	//springboot和传统springMVC不同
    @Bean
    public SignInterceptor signInterceptor(){
        return new SignInterceptor();
    }
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }
}
