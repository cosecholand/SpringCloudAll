package com.w3cjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
/**
 * 
 * @class  SpringBootMulMybatisApplication
 * @version SpringBoot 2.1.9
 * @author cos
 * @desc   整合Mybatis实现多数据源配置
 *
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringBootMulMybatisApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMulMybatisApplication.class, args);
		
	}

}
