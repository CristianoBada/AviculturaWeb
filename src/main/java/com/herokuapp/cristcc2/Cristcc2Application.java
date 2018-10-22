package com.herokuapp.cristcc2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Cristcc2Application {

	public static void main(String[] args) {
		SpringApplication.run(Cristcc2Application.class, args);
		System.out.print(new BCryptPasswordEncoder().encode("123"));
	}
}
