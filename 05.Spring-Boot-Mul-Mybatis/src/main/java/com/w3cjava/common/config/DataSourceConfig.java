package com.w3cjava.common.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.w3cjava.common.datasource.DynamicDataSource;

@Configuration
public class DataSourceConfig {
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	// 数据源master
	@Bean(name = "master")
	@ConfigurationProperties(prefix = "spring.datasource.master") // application.properteis中对应属性的前缀
	public DataSource masterDataSource() {
		return DataSourceBuilder.create().build();
	}

	// 数据源slave
	@Bean(name = "slave")
	@ConfigurationProperties(prefix = "spring.datasource.slave") // application.properteis中对应属性的前缀
	public DataSource slaveDataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * 动态数据源: 通过AOP在不同数据源之间动态切换
	 * 
	 * @return
	 */
	@Primary
	@Bean(name = "dynamicDataSource")
	public DataSource dynamicDataSource() {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		// 默认数据源
		dynamicDataSource.setDefaultTargetDataSource(masterDataSource());
		// 配置多数据源
		Map<Object, Object> dsMap = new HashMap<Object, Object>();
		dsMap.put("master", masterDataSource());
		dsMap.put("slave", slaveDataSource());

		dynamicDataSource.setTargetDataSources(dsMap);
		return dynamicDataSource;
	}

	/**
	 * 配置@Transactional注解事物
	 * 
	 * @return
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dynamicDataSource());
	}
	
}
