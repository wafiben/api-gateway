package dev.runner.apigatway.modules.auth.dto;

public class AuthResponse {
    private String token;

    AuthResponse() {
    }

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
