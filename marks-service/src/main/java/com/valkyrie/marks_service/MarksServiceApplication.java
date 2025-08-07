package com.valkyrie.marks_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MarksServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarksServiceApplication.class, args);
	}

}
