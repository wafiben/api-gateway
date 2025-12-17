package controller;

import DTO.AuthResponse;
import DTO.LoginRequest;
import DTO.RegisterRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
public class AuthGatewayController {

    private final RestTemplate restTemplate;
    private String authServiceUrl;

    public AuthGatewayController(RestTemplate restTemplate,
                                 @Value("${auth.service.url}") String authServiceUrl) {
        this.restTemplate = restTemplate;
//        this.authServiceUrl = authServiceUrl;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
//        return restTemplate.postForEntity(authServiceUrl + "/register", request, AuthResponse.class);
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        System.out.println("eeeeeeeeeeeeeeeeeeeee");
        return null;
//        System.out.println("sss login");
//        return restTemplate.postForEntity(authServiceUrl + "/login", request, AuthResponse.class);
    }
}