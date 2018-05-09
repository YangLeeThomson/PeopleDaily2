package com.ronglian.config;
/**
 * @author yanglee
 * @createTime 2018-05-09
 * @content 异步的task执行配置类，需要在job类中用指定注解@Async，加在当前job类上，或job中的task方法上；
*/
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
	@Value("${corePoolSize}") 
    private int corePoolSize;
	@Value("${maxPoolSize}")
    private int maxPoolSize;
	@Value("${queueCapacity}")
    private int queueCapacity;
	/*
		ThreadPoolTaskExecutor 这个类的源码很经典，用了多种牛X设计模式！
		继承及实现了Java.util中的Executor接口
		提醒感兴趣的荣之联兄弟们好好参阅。
	*/
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.initialize();
        return executor;
    }
}
