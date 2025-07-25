package com.keyin.club.QAP3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Qap3Application {

	public static void main(String[] args) {
		System.out.println(">>> Spring starting with: " + System.getProperty("spring.datasource.url"));
		SpringApplication.run(Qap3Application.class, args);
	}

}
