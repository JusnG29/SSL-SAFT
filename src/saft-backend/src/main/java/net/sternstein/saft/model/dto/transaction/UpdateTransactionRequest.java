package net.sternstein.saft.model.dto.transaction;

import net.sternstein.saft.domain.Transaction;

public record UpdateTransactionRequest(Transaction transaction) {
}