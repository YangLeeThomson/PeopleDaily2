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
public class PeopleDaily2Application {
	
	public static void main(String[] args) {
		SpringApplication.run(PeopleDaily2Application.class, args);
	}
	 
	    @Bean(name = "transactionManager")
	    public PlatformTransactionManager txManager(DataSource dataSource) {
	        return new DataSourceTransactionManager(dataSource);
	    }
}
