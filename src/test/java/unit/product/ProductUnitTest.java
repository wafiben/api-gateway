package unit.product;

import dev.runner.apigatway.modules.product.dto.ProductDto;
import dev.runner.apigatway.modules.product.interfaces.ProductServiceGateway;
import dev.runner.apigatway.modules.product.productbuilder.ProductBuilder;
import dev.runner.apigatway.modules.product.service.ProductServiceInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ProductUnitTest {

    private ProductServiceGateway productServiceGateway;

    @BeforeEach
    void setUp() {
        this.productServiceGateway = new ProductServiceInMemory();
    }

    @Test
    void shouldGetProducts() {
        // Arrange
        var products = buildListProducts(
                List.of("1", "2"),
                List.of("Laptop", "Phone"),
                List.of("1200", "800"),
                List.of("ELECTRONICS", "ELECTRONICS")
        );
        products.forEach(productServiceGateway::saveProduct);
        // Act
        List<ProductDto> result = productServiceGateway.listProducts();
        // Assert
        assertThat(result).hasSize(2);
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
