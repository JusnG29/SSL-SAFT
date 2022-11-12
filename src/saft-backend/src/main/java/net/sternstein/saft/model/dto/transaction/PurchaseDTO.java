package net.sternstein.saft.model.dto.transaction;

import java.util.UUID;

public record PurchaseDTO(UUID productId, int amount) {
}
