package net.sternstein.saft.service;

import net.sternstein.saft.domain.Purchase;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface PurchaseService {
    Purchase createPurchase(UUID transactionId, UUID productId, BigDecimal value, int amount);
    Purchase getPurchase(UUID id);
    List<Purchase> getAllPurchases();
    Purchase updatePurchase(Purchase purchase);
    void deletePurchase(UUID id);
}
