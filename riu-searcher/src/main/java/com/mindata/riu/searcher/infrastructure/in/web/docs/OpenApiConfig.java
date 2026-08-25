package com.mindata.riu.searcher.infrastructure.in.web.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
            .info(
                new Info()
                    .title("RIU Search API")
                    .version("1.0")
                    .description("API para la consulta y publicación de búsquedas")
                    .contact(
                        new Contact()
                            .name("Franco Ferrari")
                            .email("franconferrari02@outlook.com")
                    )
            )
            .servers(List.of(
                new Server()
                    .url("http://localhost:3500")
                    .description("Local")
            ));
    }

}
