package net.sternstein.saft.model.dto.transaction;

import net.sternstein.saft.domain.Purchase;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ExecuteTransactionRequest(UUID userId, List<PurchaseDTO> purchaseDTOList) {
}
