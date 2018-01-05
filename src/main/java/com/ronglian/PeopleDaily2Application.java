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
 * @createTime 2017��12��18��
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
	    
	    // �������������1
	    /*
	     *  �������������txManager
	     *  ���� dataSource ��ܻ��Զ�Ϊ����ע��
	     */
	    @Bean(name = "txManager1")
	    public PlatformTransactionManager txManager(DataSource dataSource) {
	        return new DataSourceTransactionManager(dataSource);
	    }

	    // �������������2
	    @Bean(name = "txManager2")
	    public PlatformTransactionManager txManager2(EntityManagerFactory factory) {
	        return new JpaTransactionManager(factory);
	    }

	    /*
	     * ʵ�ֽӿ� TransactionManagementConfigurer ������
	     * �䷵��ֵ������ӵ�ж������������������Ĭ��ʹ�õ����������(txManager2)
	     */
	    @Override
	    public PlatformTransactionManager annotationDrivenTransactionManager() {
	        return txManager2;
	    }

}
