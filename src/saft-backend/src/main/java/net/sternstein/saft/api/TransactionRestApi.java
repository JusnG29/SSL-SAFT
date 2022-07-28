package net.sternstein.saft.api;

import net.sternstein.saft.models.dtos.transaction.CreateTransactionRequest;
import net.sternstein.saft.models.dtos.transaction.DeleteTransactionRequest;
import net.sternstein.saft.models.dtos.transaction.GetTransactionRequest;
import net.sternstein.saft.models.dtos.transaction.UpdateTransactionRequest;
import net.sternstein.saft.services.TransactionService;

import javax.inject.Inject;
import javax.transaction.Transactional;
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
    @Transactional
    @Override
    public Response createTransaction(CreateTransactionRequest request) {
        var transaction = transactionService.createTransaction(request.userId(), request.productId(), request.value(), request.amount());
        return Response.ok().entity(transaction).build();
    }

    @GET
    @Path("{id}")
    @Override
    public Response getTransaction(UUID id) {
        var transaction = transactionService.getTransaction(id);
        return Response.ok().entity(transaction).build();
    }

    @GET
    @Path("all")
    @Override
    public Response getAllTransactions() {
        var transactions = transactionService.getAllTransactions();
        return Response.ok().entity(transactions).build();
    }

    @PUT
    @Transactional
    @Override
    public Response updateTransaction(UpdateTransactionRequest request) {
        var transaction = transactionService.updateTransaction(request.transaction());
        return Response.ok().entity(transaction).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Override
    public Response deleteTransaction(UUID id) {
        transactionService.deleteTransaction(id);
        return Response.ok().build();
    }
}
