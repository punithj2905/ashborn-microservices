package com.ashborn.ecommerce.product;

import org.springframework.stereotype.Repository;

import com.ashborn.ecommerce.category.Category;

import jakarta.validation.constraints.NotNull;

@Repository
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .availableQuantity(request.availableQuantity())
                .category(Category.builder()
                        .id(request.categoryId())
                        .build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {

        if (product == null) {
            return null;
        }

        Category category = product.getCategory();

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                category != null ? category.getId() : null,
                category != null ? category.getName() : null,
                category != null ? category.getDescription() : null
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product,
            double quantity) {
        return new ProductPurchaseResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            quantity


        );
    }
}
