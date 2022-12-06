package com.sojourn.sojourn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class SojournApplication {

	public static void main(String[] args) {
		SpringApplication.run(SojournApplication.class, args);
	}

	@Bean
	MessageDigest getSHA256() throws NoSuchAlgorithmException {
		return MessageDigest.getInstance("SHA-256");
	}

}
