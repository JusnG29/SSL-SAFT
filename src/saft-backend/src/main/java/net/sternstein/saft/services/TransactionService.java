package net.sternstein.saft.services;

import net.sternstein.saft.domain.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Long userId, Long productId, BigDecimal value, int amount);
    Transaction getTransaction(Long id);
    List<Transaction> getAllTransactions();
    Transaction updateTransaction(Transaction transaction);
    boolean deleteTransaction(Long id);
}
