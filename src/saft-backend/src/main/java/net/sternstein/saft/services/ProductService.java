package net.sternstein.saft.services;

import net.sternstein.saft.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product createProduct(String name, BigDecimal price);
    Product getProduct(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Product product);
    boolean deleteProduct(Long id);
}
