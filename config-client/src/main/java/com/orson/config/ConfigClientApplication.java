package com.orson.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConfigClientApplication {
	
	@Value("${owner.name}")
	String name;
	
	
	@GetMapping("/owner")
	public String owner() {
		return this.name;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

}
