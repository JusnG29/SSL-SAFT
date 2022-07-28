package net.sternstein.saft.service;

import io.quarkus.hibernate.orm.panache.Panache;
import net.sternstein.saft.domain.Product;
import net.sternstein.saft.persistence.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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
    public Product getProduct(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().toList();
    }

    @Override
    public Product updateProduct(Product product) {
        return Panache.getEntityManager().merge(product);
    }

    @Override
    public boolean deleteProduct(UUID id) {
        return productRepository.deleteById(id);
    }
}
