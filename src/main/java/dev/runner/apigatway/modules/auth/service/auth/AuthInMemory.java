package dev.runner.apigatway.modules.auth.service.auth;

import dev.runner.apigatway.modules.auth.dto.AuthResponse;
import dev.runner.apigatway.modules.auth.dto.CreateUserRequest;
import dev.runner.apigatway.modules.auth.dto.LoginRequest;
import dev.runner.apigatway.modules.auth.dto.User;
import dev.runner.apigatway.modules.auth.interfaces.AuthServiceGateway;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AuthInMemory implements AuthServiceGateway {
    private final List<User> users = new ArrayList<>();

    public AuthResponse register(CreateUserRequest request) {
        boolean exists = users.stream()
                .anyMatch(user -> user.getEmail().equals(request.getEmail()));

        if (exists) {
            throw new RuntimeException("User already exists");
        }

        User newUser = new User(
                UUID.randomUUID().toString(),
                request.getEmail(),
                request.getPassword(),
                request.getUsername()
        );

        users.add(newUser);

        String token = UUID.randomUUID().toString();
        return new AuthResponse(token);

    }

    public AuthResponse login(LoginRequest request) {
        User user = users.stream()
                .filter(u -> u.getEmail().equals(request.getEmail()))
                .filter(u -> u.getPassword().equals(request.getPassword()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        String token = UUID.randomUUID().toString();
        return new AuthResponse(token);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void clearUsers() {
        users.clear();
    }
}

