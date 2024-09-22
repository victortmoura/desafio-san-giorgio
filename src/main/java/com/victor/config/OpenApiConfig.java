package com.victor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API de Pagamentos")
                        .description("API para processar pagamentos e interagir com AWS SQS.")
                        .version("v1.0")
                        .contact(new Contact().name("Victor Moura").email("").url("")));

    }
}
