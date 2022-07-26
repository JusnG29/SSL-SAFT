package net.sternstein.saft.api;

import net.sternstein.saft.model.dto.transaction.CreateTransactionRequest;
import net.sternstein.saft.model.dto.transaction.DeleteTransactionRequest;
import net.sternstein.saft.model.dto.transaction.GetTransactionRequest;
import net.sternstein.saft.model.dto.transaction.UpdateTransactionRequest;
import net.sternstein.saft.service.TransactionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("transaction")
@Consumes("application/json")
@Produces("application/json")
public class TransactionRestApi implements TransactionApi {

    @Inject
    TransactionService transactionService;

    @POST
    @Override
    public Response createTransaction(CreateTransactionRequest request) {
        transactionService.createTransaction();
        return null;
    }

    @GET
    @Override
    public Response getTransaction(GetTransactionRequest request) {
        transactionService.getTransaction();
        return null;
    }

    @PUT
    @Override
    public Response updateTransaction(UpdateTransactionRequest request) {
        transactionService.updateTransaction();
        return null;
    }

    @DELETE
    @Override
    public Response deleteTransaction(DeleteTransactionRequest request) {
        transactionService.deleteTransaction();
        return null;
    }
}