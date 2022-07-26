package net.sternstein.saft.model.dto.transaction;

import java.math.BigDecimal;

public record CreateTransactionRequest(Long userId, Long productId, BigDecimal value, int amount) {
}
