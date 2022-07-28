package net.sternstein.saft.model.dto.transaction;

import java.math.BigDecimal;
import java.util.UUID;

public record PurchaseRequest(UUID userId, UUID productId, BigDecimal value, int amount) {
}
