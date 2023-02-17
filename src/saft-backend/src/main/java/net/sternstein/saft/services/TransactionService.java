package net.sternstein.saft.services;

import net.sternstein.saft.domain.Transaction;
import net.sternstein.saft.model.dto.transaction.PurchaseDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface TransactionService {
    Transaction createTransaction(UUID userId);
    Transaction createDepositTransaction(UUID userId, BigDecimal amount);
    Transaction getTransaction(UUID id);
    List<Transaction> getAllTransactions();
    Transaction updateTransaction(Transaction transaction);
    void deleteTransaction(UUID id);
    Transaction executeTransaction(UUID userId, List<PurchaseDTO> purchaseDTOList);
    List<Transaction> getUserHistory(UUID userId);
}
