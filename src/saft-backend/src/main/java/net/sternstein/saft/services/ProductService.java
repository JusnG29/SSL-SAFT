package net.sternstein.saft.services;

import net.sternstein.saft.domain.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product createProduct(String name, BigDecimal price, String colorCode, String description);
    Product getProduct(UUID id);
    List<Product> getAllProducts();
    Product updateProduct(Product product);
    void deleteProduct(UUID id);
}
