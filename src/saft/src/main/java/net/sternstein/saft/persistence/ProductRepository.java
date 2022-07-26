package net.sternstein.saft.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.sternstein.saft.domain.Product;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {
}
