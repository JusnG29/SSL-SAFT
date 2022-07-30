package net.sternstein.saft.model.dto.product;

import java.math.BigDecimal;

public record CreateProductRequest(String name, BigDecimal price) {
}
