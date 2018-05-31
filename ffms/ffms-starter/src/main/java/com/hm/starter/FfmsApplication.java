package com.hm.starter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan("com.hm")
@SpringBootApplication
public class FfmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FfmsApplication.class, args);
		
	}
}
