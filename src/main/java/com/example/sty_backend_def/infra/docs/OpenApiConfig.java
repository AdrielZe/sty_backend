package com.example.sty_backend_def.infra.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sty API")
                        .description("REST API for the Sty application.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Adriel")
                                .email("")));
    }
}