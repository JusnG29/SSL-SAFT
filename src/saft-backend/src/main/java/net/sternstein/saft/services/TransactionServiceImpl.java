package net.sternstein.saft.services;

import io.quarkus.hibernate.orm.panache.Panache;
import net.sternstein.saft.domain.Purchase;
import net.sternstein.saft.domain.Transaction;
import net.sternstein.saft.domain.User;
import net.sternstein.saft.model.dto.transaction.PurchaseDTO;
import net.sternstein.saft.persistence.PurchaseRepository;
import net.sternstein.saft.persistence.TransactionRepository;
import net.sternstein.saft.persistence.UserRepository;
import net.sternstein.saft.service.PurchaseService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TransactionServiceImpl implements TransactionService {

    @Inject
    PurchaseService purchaseService;
    @Inject
    TransactionRepository transactionRepository;
    @Inject
    UserRepository userRepository;
    @Inject
    PurchaseRepository purchaseRepository;

    @Override
    public Transaction createTransaction(UUID userId) {
        var user = userRepository.findById(userId);
        var transaction = new Transaction(user);

        transactionRepository.persist(transaction);

        return transaction;
    }

    @Override
    public Transaction createDepositTransaction(UUID userId, BigDecimal amount) {
        var user = userRepository.findById(userId);

        var transaction = new Transaction(user);
        transaction.setPurchaseDate(Instant.now());
        transaction.setTotalValue(amount);
        transactionRepository.persist(transaction);

        return transaction;
    }

    @Override
    public Transaction getTransaction(UUID id) {
        return transactionRepository.findById(id);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.listAll();
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
    public Transaction executeTransaction(UUID userId, List<PurchaseDTO> purchaseDTOList) {

        Transaction transaction = createTransaction(userId);

        List<Purchase> purchaseList = purchaseService.createFromDtoList(transaction, purchaseDTOList);
        purchaseRepository.persist(purchaseList);

        BigDecimal totalValue = purchaseService.calculateTotalValue(purchaseList);

        transaction.setTotalValue(totalValue);
        User user = userRepository.findById(userId);
        BigDecimal newBalance = user.getBalance().subtract(totalValue);
        user.setBalance(newBalance);

        Panache.getEntityManager().merge(user);

        // TODO: @Jusn Hacky way to get purchase list as well
        var fullTransaction = Panache.getEntityManager().merge(transaction);
        fullTransaction.setPurchaseList(purchaseList);
        return fullTransaction;
    }

    @Override
    public List<Transaction> getUserHistory(UUID userId) {
        return transactionRepository.getAllTransactionsByUserId(userId);
    }
}
