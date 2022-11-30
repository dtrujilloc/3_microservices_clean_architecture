package com.challenge.hexagonal.msphoto.infrastructure.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase que representa la configuracion para la documentacion de Open API
 * @author dtrujilloc
 * @version 1.0 29/11/2022
 */
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenApi(@Value("${appDescription}") String appDescription, @Value("${appVersion}") String appVersion){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Photo API Hexagonal Architecture")
                        .version(appVersion)
                        .description(appDescription)
                );
    }
}
