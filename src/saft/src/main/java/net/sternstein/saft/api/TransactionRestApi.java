package net.sternstein.saft.api;

import net.sternstein.saft.model.dto.transaction.CreateTransactionRequest;
import net.sternstein.saft.model.dto.transaction.UpdateTransactionRequest;
import net.sternstein.saft.service.TransactionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("transaction")
@Consumes("application/json")
@Produces("application/json")
public class TransactionRestApi implements TransactionApi {

    @Inject
    TransactionService transactionService;

    @POST
    @Override
    public Response createTransaction(CreateTransactionRequest request) {
        var transaction = transactionService.createTransaction(request.userId(), request.productId(), request.value(), request.amount());
        return Response.ok().entity(transaction).build();
    }

    @GET
    @Override
    public Response getTransaction(UUID id) {
        var transaction = transactionService.getTransaction(id);
        return Response.ok().entity(transaction).build();
    }

    @GET
    // TODO: check path ok?
    @Path("all")
    @Override
    public Response getAllTransactions() {
        var transactions = transactionService.getAllTransactions();
        return Response.ok().entity(transactions).build();
    }

    @PUT
    @Override
    public Response updateTransaction(UpdateTransactionRequest request) {
        var transaction = transactionService.updateTransaction(request.transaction());
        return Response.ok().entity(transaction).build();
    }

    @DELETE
    @Override
    public Response deleteTransaction(UUID id) {
        boolean isRemoved = transactionService.deleteTransaction(id);
        if(!isRemoved) {
            // TODO: do this the right way
            return Response.serverError().build();
        }
        return Response.ok().build();
    }
}
