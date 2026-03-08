package endtoend;


import dev.runner.apigatway.ApiGatewayApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        classes = ApiGatewayApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class AbstractTest {
}
