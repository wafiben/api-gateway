package dev.runner.apigatway.modules.product.productbuilder;

import dev.runner.apigatway.modules.product.dto.ProductDto;

public class ProductBuilder {

    private String id;
    private String name;
    private String price;
    private String category;

    public ProductBuilder id(String id) {
        this.id = id;
        return this;
    }

    public ProductBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder price(String price) {
        this.price = price;
        return this;
    }

    public ProductBuilder category(String category) {
        this.category = category;
        return this;
    }

    public ProductDto build() {
        return new ProductDto(id, name, price, category);
    }
}

