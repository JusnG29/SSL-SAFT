package net.sternstein.saft.api;

import net.sternstein.saft.model.dto.transaction.CreateTransactionRequest;
import net.sternstein.saft.model.dto.transaction.UpdateTransactionRequest;

import javax.ws.rs.core.Response;
import java.util.UUID;

public interface TransactionApi {
    Response createTransaction(CreateTransactionRequest request);
    Response getTransaction(UUID id);
    Response getAllTransactions();
    Response updateTransaction(UpdateTransactionRequest request);
    Response deleteTransaction(UUID id);
}
