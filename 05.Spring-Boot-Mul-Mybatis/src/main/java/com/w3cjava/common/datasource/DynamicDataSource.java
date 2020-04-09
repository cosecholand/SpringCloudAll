package com.w3cjava.common.datasource;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	@Override
	protected Object determineCurrentLookupKey() {
		System.out.println("数据源为" + DataSourceContextHolder.getDb());
		return DataSourceContextHolder.getDb();
	}
	
	
}
