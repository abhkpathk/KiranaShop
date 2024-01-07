package com.example.KiranaAssignment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Kirana Shop", version = "1.0", description = "Kirana Management"))
public class KiranaAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(KiranaAssignmentApplication.class, args);
	}

}
