package com.infytel.InfytelUnitTesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class InfytelUnitTestingApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(InfytelUnitTestingApplication.class, args);
	}

}
