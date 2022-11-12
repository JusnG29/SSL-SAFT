package net.sternstein.saft.service;

import io.quarkus.hibernate.orm.panache.Panache;
import net.sternstein.saft.domain.Product;
import net.sternstein.saft.domain.Purchase;
import net.sternstein.saft.domain.Transaction;
import net.sternstein.saft.model.dto.transaction.PurchaseDTO;
import net.sternstein.saft.persistence.ProductRepository;
import net.sternstein.saft.persistence.PurchaseRepository;
import net.sternstein.saft.persistence.TransactionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return purchaseRepository.listAll();
    }

    @Override
    public Purchase updatePurchase(Purchase purchase) {
        return Panache.getEntityManager().merge(purchase);
    }

    @Override
    public void deletePurchase(UUID id) {
        purchaseRepository.deleteById(id);
    }

    @Override
    public List<Purchase> createFromDtoList(Transaction transaction, List<PurchaseDTO> purchaseDTOList) {
        List<Purchase> purchaseList = purchaseDTOList.stream().map(dto -> transformFromDto(transaction, dto)).collect(Collectors.toList());
        purchaseRepository.persist(purchaseList);
        return purchaseList;
    }

    @Override
    public BigDecimal calculateTotalValue(List<Purchase> purchaseList) {
        return purchaseList.stream().map(purchase -> purchase.getValue().multiply(BigDecimal.valueOf(purchase.getAmount()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Purchase transformFromDto(Transaction transaction, PurchaseDTO purchaseDTO) {
        Product product = productRepository.findById(purchaseDTO.productId());
        return new Purchase(transaction, product, product.getPrice(), purchaseDTO.amount());
    }
}
