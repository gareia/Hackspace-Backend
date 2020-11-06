package com.acme.hackspace.configuration;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class OpenApiConfig {
    @Bean
    public OpenAPI tasksOpenAPI(){
        return new OpenAPI().components(new Components()).info(
            new Info().title("Hackspace tasks API")
            .description("To-do sample application")
            .version("v1.0")
        );
    }
}
