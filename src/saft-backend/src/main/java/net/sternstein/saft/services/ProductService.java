package net.sternstein.saft.services;

import net.sternstein.saft.domain.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product createProduct(String name, BigDecimal price);
    Product getProduct(UUID id);
    List<Product> getAllProducts();
    Product updateProduct(Product product);
    boolean deleteProduct(UUID id);
}
