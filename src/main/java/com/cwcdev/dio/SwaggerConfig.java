package com.cwcdev.dio;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Santander")
                .version("1.0")
                .description("API para Bootcamp Santander")
                .contact(new Contact()
                    .name("Calebe Werneck Couto")
                    .email("calebewerneck@hotmail.com")
                    .url("https://www.linkedin.com/in/calebe-werneck-571091295/")
                )
            )
            .externalDocs(new ExternalDocumentation()
                .description("Portfólio")
                .url("https://cwc3d.net")
            );
    }
}
