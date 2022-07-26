package net.sternstein.saft.api;

import net.sternstein.saft.model.dto.transaction.CreateTransactionRequest;
import net.sternstein.saft.model.dto.transaction.DeleteTransactionRequest;
import net.sternstein.saft.model.dto.transaction.GetTransactionRequest;
import net.sternstein.saft.model.dto.transaction.UpdateTransactionRequest;

import javax.ws.rs.core.Response;

public interface TransactionApi {
    Response createTransaction(CreateTransactionRequest request);
    Response getTransaction(GetTransactionRequest request);
    Response updateTransaction(UpdateTransactionRequest request);
    Response deleteTransaction(DeleteTransactionRequest request);
}
