package com.isacore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
public class SpringBootIsa {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIsa.class, args);
	}

}
