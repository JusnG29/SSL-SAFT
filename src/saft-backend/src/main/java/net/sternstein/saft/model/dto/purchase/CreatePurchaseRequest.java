package net.sternstein.saft.model.dto.purchase;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatePurchaseRequest(UUID transactionId, UUID productId, BigDecimal value, int amount) {
}
