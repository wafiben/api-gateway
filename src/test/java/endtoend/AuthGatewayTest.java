package endtoend;

import DTO.AuthResponse;
import DTO.RegisterRequest;
import dev.runner.apigatway.ApiGatwayApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = ApiGatwayApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestPropertySource(properties = {
        "auth.service.url=http://localhost:8081/auth"
})
public class AuthGatewayTest {

//    @Value("${auth.service.url}")
//    private String authServiceUrl;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void testRegisterThroughGateway() {
//        System.out.println("Auth service URL: " + authServiceUrl);
//
//        RegisterRequest request = new RegisterRequest();
//        request.setName("Wafi");
//        request.setEmail("example@example.com");
//        request.setUsername("wafi");
//        request.setPassword("securePassword");
//
//        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(
//                "/auth/register",
//                request,
//                AuthResponse.class
//        );
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
}
