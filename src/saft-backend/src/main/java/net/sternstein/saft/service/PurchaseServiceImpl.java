package net.sternstein.saft.service;

import io.quarkus.hibernate.orm.panache.Panache;
import net.sternstein.saft.domain.Product;
import net.sternstein.saft.domain.Purchase;
import net.sternstein.saft.domain.Transaction;
import net.sternstein.saft.persistence.ProductRepository;
import net.sternstein.saft.persistence.PurchaseRepository;
import net.sternstein.saft.persistence.TransactionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PurchaseServiceImpl implements PurchaseService {

    @Inject
    PurchaseRepository purchaseRepository;
    @Inject
    TransactionRepository transactionRepository;
    @Inject
    ProductRepository productRepository;

    @Override
    public Purchase createPurchase(UUID transactionId, UUID productId, BigDecimal value, int amount) {
        Transaction transaction = transactionRepository.findById(transactionId);
        Product product = productRepository.findById(productId);
        var purchase = new Purchase(transaction, product, value, amount);

        purchaseRepository.persist(purchase);

        return purchase;
    }

    @Override
    public Purchase getPurchase(UUID id) {
        return purchaseRepository.findById(id);
    }

    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll().stream().toList();
    }

    @Override
    public Purchase updatePurchase(Purchase purchase) {
        return Panache.getEntityManager().merge(purchase);
    }

    @Override
    public void deletePurchase(UUID id) {
        purchaseRepository.deleteById(id);
    }
}
