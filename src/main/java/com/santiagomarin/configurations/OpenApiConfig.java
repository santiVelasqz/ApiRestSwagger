package com.santiagomarin.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	
	@Bean
	OpenAPI apiInfo() {
		return new OpenAPI()
				.info(new Info()
						.title("e-commence Management API")
                        .description("RESTful API for managing e-commence with CRUD operations")
						.version("v1.0.0")
						.contact(new Contact()
								.name("Santiago")
								.email("santi@api.com")));
	}

}
