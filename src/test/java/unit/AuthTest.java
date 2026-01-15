package unit;

import dev.runner.apigatway.modules.auth.builders.UserBuilder;
import dev.runner.apigatway.modules.auth.dto.AuthResponse;
import dev.runner.apigatway.modules.auth.dto.CreateUserRequest;
import dev.runner.apigatway.modules.auth.dto.LoginRequest;
import dev.runner.apigatway.modules.auth.service.auth.AuthInMemory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTest {

    private AuthInMemory authService;

    @BeforeEach
    void setup() {
        authService = new AuthInMemory();

        var sarah = UserBuilder.newUser()
                .withEmail("john@test.com")
                .withPassword("john")
                .withUsername("john@test.com")
                .build();

        var admin = UserBuilder.newUser()
                .withEmail("admin@test.com")
                .withPassword("admin")
                .withUsername("admin")
                .build();

        authService.addUser(sarah);
        authService.addUser(admin);
    }

    @AfterEach
    void tearDown() {
        authService.clearUsers();
    }

    @Test
    void shouldRegisterUser() {
        CreateUserRequest request = new CreateUserRequest(
                "john@test.com",
                "1234",
                "john"
        );

        AuthResponse response = authService.register(request);
        assertNotNull(response.getToken());
    }

    @Test
    void shouldFailWhenRegisteringExistingEmail() {
        CreateUserRequest request = new CreateUserRequest(
                "john@test.com",
                "1234",
                "john"
        );

        authService.register(request);

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                authService.register(request)
        );

        assertEquals("User already exists", ex.getMessage());
        assertEquals(false,true);
    }

    @Test
    void shouldLoginUser() {
        LoginRequest loginRequest = new LoginRequest(
                "john@test.com",
                "john"
        );
        authService.login(loginRequest);

        AuthResponse response = authService.login(loginRequest);

        assertNotNull(response.getToken());
    }
}
