package com.schedulecore.ufu.infrasctructure.api.configs.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI scheduleCoreOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Schedule-Core UFU API")
                        .description("API para agendamento de espaços esportivos da UFU")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipe Schedule-Core")
                                .email("gabriel_machado@ufu.br")));
    }
}
