package com.esteel.web;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableDubbo
@EnableRedisRepositories
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class }, scanBasePackages = { "com.esteel.web" })
public class ApiWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiWebApplication.class, args);
	}

}
