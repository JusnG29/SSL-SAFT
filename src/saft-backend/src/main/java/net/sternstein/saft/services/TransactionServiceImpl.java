package net.sternstein.saft.services;

import io.quarkus.hibernate.orm.panache.Panache;
import net.sternstein.saft.domain.Purchase;
import net.sternstein.saft.domain.Transaction;
import net.sternstein.saft.domain.User;
import net.sternstein.saft.model.dto.transaction.PurchaseDTO;
import net.sternstein.saft.persistence.PurchaseRepository;
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

        var transaction = createTransaction(userId);

        List<Purchase> purchaseList = purchaseService.createFromDtoList(transaction, purchaseDTOList);
        purchaseRepository.persist(purchaseList);

        User user = userRepository.findById(userId);
        BigDecimal newBalance = user.getBalance().subtract(purchaseService.calculateTotalValue(purchaseList));
        user.setBalance(newBalance);

        Panache.getEntityManager().merge(user);

        return transaction;
    }

    @Override
    public List<Transaction> getUserHistory(UUID userId) {
        return transactionRepository.getAllTransactionsByUserId(userId);
    }
}
