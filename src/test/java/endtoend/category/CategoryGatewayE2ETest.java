package endtoend.category;

import dev.runner.apigatway.modules.category.dto.CategoryDto;
import dev.runner.apigatway.modules.category.dto.GetCategoryRequest;
import dev.runner.apigatway.modules.category.service.category.CategoryServiceImp;
import endtoend.AbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class CategoryGatewayE2ETest extends AbstractTest {
    private CategoryServiceImp gateway;
    private MockRestServiceServer mockServer;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setup() {
        RestTemplate restTemplate = new RestTemplate();
        gateway = new CategoryServiceImp(restTemplate, "http://localhost:8083/categories");
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void should_return_all_categories() {
        String mockResponseJson = "[{\"id\":\"1\",\"name\":\"Sport\"},{\"id\":\"2\",\"name\":\"Music\"}]";
        mockServer.expect(requestTo("http://localhost:8083/categories"))
                .andRespond(withSuccess(mockResponseJson, MediaType.APPLICATION_JSON));

        List<CategoryDto> response = gateway.categoryList(new GetCategoryRequest(null, null));

        assertNotNull(response);
        assertEquals(2, response.size());
        mockServer.verify();
    }

    @Test
    void should_filter_by_name() {
        String mockResponseJson = "[{\"id\":\"1\",\"name\":\"Sport\"}]";
        mockServer.expect(requestTo("http://localhost:8083/categories?name=Sport"))
                .andRespond(withSuccess(mockResponseJson, MediaType.APPLICATION_JSON));

        List<CategoryDto> response = gateway.categoryList(new GetCategoryRequest("Sport", null)); // ← name en premier

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Sport", response.get(0).getName());
        mockServer.verify();
    }
}
