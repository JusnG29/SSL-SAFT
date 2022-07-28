package net.sternstein.saft.services;

import io.quarkus.hibernate.orm.panache.Panache;
import net.sternstein.saft.domain.Transaction;
import net.sternstein.saft.domain.User;
import net.sternstein.saft.persistence.ProductRepository;
import net.sternstein.saft.persistence.TransactionRepository;
import net.sternstein.saft.persistence.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TransactionServiceImpl implements TransactionService {

    @Inject
    UserRepository userRepository;
    @Inject
    ProductRepository productRepository;
    @Inject
    TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(UUID userId, UUID productId, BigDecimal value, int amount) {
        var user = userRepository.findById(userId);
        var product = productRepository.findById(productId);
        var transaction = new Transaction(user, product, value, amount);

        transactionRepository.persist(transaction);

        return transaction;
    }

    @Override
    public Transaction getTransaction(UUID id) {
        return transactionRepository.findById(id);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll().stream().toList();
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return Panache.getEntityManager().merge(transaction);
    }

    @Override
    public void deleteTransaction(UUID id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Transaction purchase(UUID userId, UUID productId, BigDecimal value, int amount) {
        var transaction = createTransaction(userId, productId, value, amount);

        User user = userRepository.findById(userId);
        BigDecimal newBalance = user.getBalance().subtract(value.multiply(BigDecimal.valueOf(amount)));
        user.setBalance(newBalance);

        Panache.getEntityManager().merge(user);

        return transaction;
    }

    @Override
    public List<Transaction> getUserHistory(UUID userId) {
        return transactionRepository.getAllTransactionsByUserId(userId);
    }
}
