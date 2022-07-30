package net.sternstein.saft.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import net.sternstein.saft.domain.Transaction;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TransactionRepository implements PanacheRepositoryBase<Transaction, UUID> {
    public List<Transaction> getAllTransactionsByUserId(UUID id) {
        return this.list("userId = ?1", id);
    }
}
