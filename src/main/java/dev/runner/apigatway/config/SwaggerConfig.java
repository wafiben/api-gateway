package dev.runner.apigatway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.server.url:http://localhost:9000}")
    private String serverUrl;

    @Value("${swagger.server.description:Development Server}")
    private String serverDescription;

    @Bean
    public OpenAPI apiGatewayOpenAPI() {
        List<Server> servers = new ArrayList<>();

        // Add the configured server (can be localhost or production URL)
        servers.add(new Server()
                .url(serverUrl)
                .description(serverDescription));

        return new OpenAPI()
                .info(new Info()
                        .title("API Gateway")
                        .description("Microservices API Gateway - Routes to Auth, Users, and Products services")
                        .version("1.0.0"))
                .servers(servers);
    }
}