package com.umut.ubank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class UbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(UbankApplication.class, args);
	}

}
