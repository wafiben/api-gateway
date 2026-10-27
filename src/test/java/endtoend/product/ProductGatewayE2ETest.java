package endtoend.product;

import dev.runner.apigatway.config.Endpoints;
import dev.runner.apigatway.modules.product.ProductGatewayController;
import dev.runner.apigatway.modules.product.dto.ProductDto;
import dev.runner.apigatway.modules.product.interfaces.ProductServiceGateway;
import dev.runner.apigatway.modules.product.productbuilder.ProductBuilder;
import dev.runner.apigatway.modules.product.service.ProductServiceInMemory;
import endtoend.AbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductGatewayE2ETest extends AbstractTest {

    private MockMvc mockMvc;

    private ProductServiceGateway productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceInMemory();
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ProductGatewayController(productService))
                .build();

        var products = buildListProducts(
                List.of("1", "2"),
                List.of("Laptop", "Phone"),
                List.of("1200", "800"),
                List.of("ELECTRONICS", "ELECTRONICS")
        );

        productService.saveProduct(products.get(0));
        productService.saveProduct(products.get(1));
    }

    @Test
    void should_return_200_and_list_products() throws Exception {
        mockMvc.perform(get(Endpoints.PRODUCT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("Phone"))
                .andExpect(jsonPath("$[0].category").value("ELECTRONICS"));
    }

    private ProductDto buildOneProduct(String id,
                                       String name,
                                       String price,
                                       String category) {
        return new ProductBuilder().id(id).name(name)
                .category(category).
                price(price).
                build();
    }

    private List<ProductDto> buildListProducts(
            List<String> ids,
            List<String> names,
            List<String> prices,
            List<String> categories
    ) {
        return java.util.stream.IntStream.range(0, ids.size())
                .mapToObj(i -> buildOneProduct(
                        ids.get(i),
                        names.get(i),
                        prices.get(i),
                        categories.get(i)
                ))
                .toList();
    }
}