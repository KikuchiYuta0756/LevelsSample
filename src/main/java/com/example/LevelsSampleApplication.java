package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LevelsSampleApplication {

	public static void main(String[] args) {
		    SpringApplication.run(LevelsSampleApplication.class, args);

//	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//	        String rawPassword = "test00009";
//	        String hashedPassword = encoder.encode(rawPassword);
//	        System.out.println(hashedPassword);
		    
//	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//	        String rawPassword = "test1111";
//	        String hashedPassword = encoder.encode(rawPassword);
//	        System.out.println(hashedPassword);
		    
	}

}
