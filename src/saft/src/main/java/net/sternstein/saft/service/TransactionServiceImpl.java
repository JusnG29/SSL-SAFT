package net.sternstein.saft.service;

import net.sternstein.saft.domain.Transaction;
import net.sternstein.saft.persistence.ProductRepository;
import net.sternstein.saft.persistence.TransactionRepository;
import net.sternstein.saft.persistence.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class TransactionServiceImpl implements TransactionService {

    @Inject
    UserRepository userRepository;
    @Inject
    ProductRepository productRepository;
    @Inject
    TransactionRepository transactionRepository;

    @Override
    // TODO: GAJ ID!
    public Transaction createTransaction(Long userId, Long productId, BigDecimal value, int amount) {
        var user = userRepository.findById(userId);
        var product = productRepository.findById(productId);
        var transaction = new Transaction(user, product, value, amount);

        transactionRepository.persist(transaction);

        return transaction;
    }

    @Override
    // TODO: GAJ ID!
    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll().stream().toList();
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        transactionRepository.persist(transaction);
        return transaction;
    }

    @Override
    // TODO: GAJ ID!
    public boolean deleteTransaction(Long id) {
        return transactionRepository.deleteById(id);
    }
}
