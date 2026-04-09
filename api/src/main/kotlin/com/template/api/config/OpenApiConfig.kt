package com.template.api.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .info(
            Info()
                .title("Template Kotlin API")
                .description("Spring Boot 3.5 Kotlin template API with modular architecture")
                .version("v1.0.0")
                .contact(
                    Contact()
                        .name("Team")
                        .email("team@example.com")
                )
                .license(
                    License()
                        .name("MIT")
                )
        )
}
