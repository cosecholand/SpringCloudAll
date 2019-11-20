package com.w3cjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * 
 * @class  SpringBootMybatisApplication
 * @version SpringBoot 2.1.9
 * @author cos
 * @desc   整合Mybatis
 *
 */
@SpringBootApplication
@EnableTransactionManagement
public class SpringBootMybatisApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisApplication.class, args);
	}

}
