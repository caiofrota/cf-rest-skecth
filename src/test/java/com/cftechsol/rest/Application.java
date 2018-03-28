package com.cftechsol.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootApplication
@EnableConfigurationProperties
@SpringBootTest
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}