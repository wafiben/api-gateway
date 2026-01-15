package dev.runner.apigatway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiGatewayOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Gateway")
                        .description("Microservices API Gateway - Routes to Auth, Users, and Products services")
                        .version("1.0.0"))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:9000")
                                .description("Development Server")
                ));
    }
}