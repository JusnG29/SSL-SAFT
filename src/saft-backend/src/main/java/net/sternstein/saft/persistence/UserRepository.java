package net.sternstein.saft.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.sternstein.saft.domain.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
