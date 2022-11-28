package com.challenge.hexagonal.msclient.infrastructure.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase que representa la configuracion para la documentacion de de Open API
 */
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenApi(@Value("${appDescription}") String appDescription, @Value("${appVersion}") String appVersion){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Client API Hexagonal Architecture")
                        .version(appVersion)
                        .description(appDescription)
                );
    }
}
