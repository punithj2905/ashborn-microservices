package com.ashborn.ecommerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
    @NotNull(message="Product is mandotory")
    Integer productId,
    @NotNull(message="Quantity is manditory")
    double quantity
) {


}
