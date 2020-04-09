package com.w3cjava.common.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.w3cjava.common.annotation.DataBaseSource;

@Aspect
@Component
public class DataSourceExchange{
	public Logger logger = LoggerFactory.getLogger(this.getClass());
    @Pointcut(value="execution(* com.w3cjava.modules.*.dao.*.*(..))")
    public void dbPointCut() {

    }
	/**
	 * 方法结束后
	 */
	@AfterReturning(value="execution(* com.w3cjava.modules.*.dao.*.*(..)) ")
	public void afterReturning(JoinPoint point){
		logger.info("当前1数据源："+DataSourceContextHolder.getDb());
		DataSourceContextHolder.clearDB();
		logger.info("数据源已移除！");
		logger.info("当前2数据源："+DataSourceContextHolder.getDb());
	}
	
	/**
	 * 拦截目标方法，获取由@DataSource指定的数据源标识，设置到线程存储中以便切换数据源
	 */
	
	@SuppressWarnings("rawtypes")
	@Before(value="execution(* com.w3cjava.modules.*.dao.*.*(..))")
	public void before(JoinPoint point){
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        
		try {
			Method method = className.getMethod(methodName, argClass);
			DataBaseSource dataSource = AnnotationUtils.findAnnotation(method, DataBaseSource.class);
			if(dataSource!=null) {
				DataSourceContextHolder.setDB(dataSource.value());
			}else {
				DataSourceContextHolder.setDB(DataSourceContextHolder.DEFAULT_DS);
			}
			logger.info("数据源切换至："+DataSourceContextHolder.getDb());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
