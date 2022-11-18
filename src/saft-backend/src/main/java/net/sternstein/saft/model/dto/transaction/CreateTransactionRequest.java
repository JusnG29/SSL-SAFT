package net.sternstein.saft.model.dto.transaction;

import java.util.UUID;

public record CreateTransactionRequest(UUID userId) {
}