package com.example.core.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 2.x.x 버전: localhost:8080/swagger-ui.html
 * 3.x.x 버전: localhost:8080/swagger-ui/index.html
 * */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Book Service API SPEC")
                .description("API Specification About Book Service.\n")
                .version("1.0.0");
    }
}
