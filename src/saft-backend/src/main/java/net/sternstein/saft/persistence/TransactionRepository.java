package net.sternstein.saft.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.sternstein.saft.domain.Transaction;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionRepository implements PanacheRepository<Transaction> {
}
