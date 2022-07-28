package net.sternstein.saft.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import net.sternstein.saft.domain.Transaction;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class TransactionRepository implements PanacheRepositoryBase<Transaction, UUID> {
}
