package com.studentdemo.studentdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class StudentdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentdemoApplication.class, args);
	}

}
