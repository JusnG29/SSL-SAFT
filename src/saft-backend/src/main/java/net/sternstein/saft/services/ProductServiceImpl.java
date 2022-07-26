package net.sternstein.saft.services;

import net.sternstein.saft.domain.Product;
import net.sternstein.saft.persistence.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    @Inject
    ProductRepository productRepository;

    @Override
    public Product createProduct(String name, BigDecimal price) {
        var product = new Product(name, price);

        productRepository.persist(product);

        return product;
    }

    @Override
    // TODO: GAJ ID!
    public Product getProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().toList();
    }

    @Override
    public Product updateProduct(Product product) {
        productRepository.persist(product);
        return product;
    }

    @Override
    // TODO: GAJ ID!
    public boolean deleteProduct(Long id) {
        return productRepository.deleteById(id);
    }
}
