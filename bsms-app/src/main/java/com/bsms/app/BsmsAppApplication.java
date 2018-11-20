package com.bsms.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableEurekaClient
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.bsms.app.mapper")
public class BsmsAppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BsmsAppApplication.class, args);
	}
	
}
