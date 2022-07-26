package net.sternstein.saft.api;

import net.sternstein.saft.models.dtos.transaction.CreateTransactionRequest;
import net.sternstein.saft.models.dtos.transaction.UpdateTransactionRequest;

import javax.ws.rs.core.Response;

public interface TransactionApi {
    Response createTransaction(CreateTransactionRequest request);
    Response getTransaction(Long id);
    Response getAllTransactions();
    Response updateTransaction(UpdateTransactionRequest request);
    Response deleteTransaction(Long id);
}
