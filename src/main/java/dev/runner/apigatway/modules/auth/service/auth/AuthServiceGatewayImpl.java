package dev.runner.apigatway.modules.auth.service.auth;

import dev.runner.apigatway.modules.auth.dto.AuthResponse;
import dev.runner.apigatway.modules.auth.dto.CreateUserRequest;
import dev.runner.apigatway.modules.auth.dto.LoginRequest;
import dev.runner.apigatway.modules.auth.interfaces.AuthServiceGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AuthServiceGatewayImpl implements AuthServiceGateway {
    private final RestTemplate restTemplate;
    private final String authServiceUrl;

    public AuthServiceGatewayImpl(RestTemplate restTemplate,
                                  @Value("${services.auth}") String authServiceUrl) {
        this.restTemplate = restTemplate;
        this.authServiceUrl = authServiceUrl;
    }

    public AuthResponse register(CreateUserRequest request) {
        return restTemplate.postForObject(authServiceUrl + "/register", request, AuthResponse.class);
    }

    public AuthResponse login(LoginRequest request) {
        return restTemplate.postForObject(authServiceUrl + "/login", request, AuthResponse.class);
    }
}