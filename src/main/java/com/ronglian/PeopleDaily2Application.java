package com.ronglian;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
/**
 * @author liyang
 * @createTime 2017年12月18日
 */
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class PeopleDaily2Application implements TransactionManagementConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(PeopleDaily2Application.class, args);
	}
	 @Resource(name="txManager2")
	    private PlatformTransactionManager txManager2;
	    
	    // 创建事务管理器1
	    /*
	     *  配置事务管理器txManager
	     *  其中 dataSource 框架会自动为我们注入
	     */
	    @Bean(name = "txManager1")
	    public PlatformTransactionManager txManager(DataSource dataSource) {
	        return new DataSourceTransactionManager(dataSource);
	    }

	    // 创建事务管理器2
	    @Bean(name = "txManager2")
	    public PlatformTransactionManager txManager2(EntityManagerFactory factory) {
	        return new JpaTransactionManager(factory);
	    }

	    /*
	     * 实现接口 TransactionManagementConfigurer 方法，
	     * 其返回值代表在拥有多个事务管理器的情况下默认使用的事务管理器(txManager2)
	     */
	    @Override
	    public PlatformTransactionManager annotationDrivenTransactionManager() {
	        return txManager2;
	    }

}
