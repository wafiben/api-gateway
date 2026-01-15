package dev.runner.apigatway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${server.url:http://localhost:9000}")
    private String serverUrl;

    @Bean
    public OpenAPI apiGatewayOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Gateway")
                        .description("Microservices API Gateway - Routes to Auth, Users, and Products services")
                        .version("1.0.0"))
                .servers(List.of(
                        new Server()
                                .url(serverUrl)
                                .description("API Server")
                ));
    }
}