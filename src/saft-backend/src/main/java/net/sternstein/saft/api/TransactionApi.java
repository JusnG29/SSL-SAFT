package net.sternstein.saft.api;

import net.sternstein.saft.models.dtos.transaction.CreateTransactionRequest;
import net.sternstein.saft.models.dtos.transaction.UpdateTransactionRequest;

import javax.ws.rs.core.Response;
import java.util.UUID;

public interface TransactionApi {
    Response createTransaction(CreateTransactionRequest request);
    Response getTransaction(UUID id);
    Response getAllTransactions();
    Response updateTransaction(UpdateTransactionRequest request);
    Response deleteTransaction(UUID id);
    Response executeTransaction(ExecuteTransactionRequest request);
    Response getUserHistory(GetUserHistoryRequest request);
}
