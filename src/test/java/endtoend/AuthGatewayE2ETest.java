package endtoend;

import dev.runner.apigatway.ApiGatewayApplication;
import dev.runner.apigatway.modules.auth.dto.AuthResponse;
import dev.runner.apigatway.modules.auth.dto.CreateUserRequest;
import dev.runner.apigatway.modules.auth.dto.LoginRequest;
import dev.runner.apigatway.modules.auth.service.auth.AuthServiceGatewayImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest(
        classes = ApiGatewayApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class AuthGatewayE2ETest {

    private AuthServiceGatewayImpl gateway;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestTemplate restTemplate = new RestTemplate();
        gateway = new AuthServiceGatewayImpl(restTemplate, "http://localhost:8081");
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void shouldRegisterUser() {

        // Arrange
        CreateUserRequest request = new CreateUserRequest("alice@test.com", "1234", "alice");

        // Mock the HTTP response from Auth service
        String mockResponseJson = "{\"token\":\"fake-register-token\"}";
        mockServer.expect(requestTo("http://localhost:8081/register"))
                .andRespond(withSuccess(mockResponseJson, MediaType.APPLICATION_JSON));

        AuthResponse response = gateway.register(request);
        // Assert
        assertNotNull(response);
        assertEquals("fake-register-token", response.getToken());

        mockServer.verify();

    }

    @Test
    void shouldLoginUser() {
        // Arrange
        LoginRequest request = new LoginRequest("alice@test.com", "1234");

        // Mock the HTTP response from Auth service
        String mockResponseJson = "{\"token\":\"fake-login-token\"}";
        mockServer.expect(requestTo("http://localhost:8081/login"))
                .andRespond(withSuccess(mockResponseJson, MediaType.APPLICATION_JSON));

        // Act
        AuthResponse response = gateway.login(request);

        // Assert
        assertNotNull(response);
        assertEquals("fake-login-token", response.getToken());
        assertEquals(false,true);

        // Verify
        mockServer.verify();
    }
}
