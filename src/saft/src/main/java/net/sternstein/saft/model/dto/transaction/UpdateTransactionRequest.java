package net.sternstein.saft.model.dto.transaction;

import net.sternstein.saft.domain.Transaction;

// TODO: check serialization stuff
public record UpdateTransactionRequest(Transaction transaction) {
}
