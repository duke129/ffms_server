package com.hm.starter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableJpaRepositories("com.hm")
@EntityScan("com.hm")
@ComponentScan("com.hm")
@SpringBootApplication
public class FfmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FfmsApplication.class, args);
		
	}
}
