package dev.runner.apigatway.modules.auth.interfaces;

import dev.runner.apigatway.modules.auth.dto.AuthResponse;
import dev.runner.apigatway.modules.auth.dto.CreateUserRequest;
import dev.runner.apigatway.modules.auth.dto.LoginRequest;

public interface AuthServiceGateway {
    AuthResponse register(CreateUserRequest request);

    AuthResponse login(LoginRequest request);
}
