package net.sternstein.saft.services;

import net.sternstein.saft.domain.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface TransactionService {
    Transaction createTransaction(UUID userId, UUID productId, BigDecimal value, int amount);
    Transaction getTransaction(UUID id);
    List<Transaction> getAllTransactions();
    Transaction updateTransaction(Transaction transaction);
    void deleteTransaction(UUID id);
    Transaction purchase(UUID userId, UUID productId, BigDecimal value, int amount);
}
