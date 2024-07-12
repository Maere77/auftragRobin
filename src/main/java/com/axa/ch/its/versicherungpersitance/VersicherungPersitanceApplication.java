package com.axa.ch.its.versicherungpersitance;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "My API ", version = "1.0", description = "My API for managing contracts"))
@SpringBootApplication
public class VersicherungPersitanceApplication {


	public static void main(String[] args) {
		SpringApplication.run(VersicherungPersitanceApplication.class, args);
	}

}