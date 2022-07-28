package net.sternstein.saft.api;

import net.sternstein.saft.models.dtos.transaction.CreateTransactionRequest;
import net.sternstein.saft.models.dtos.transaction.DeleteTransactionRequest;
import net.sternstein.saft.models.dtos.transaction.GetTransactionRequest;
import net.sternstein.saft.models.dtos.transaction.UpdateTransactionRequest;
import net.sternstein.saft.services.TransactionService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    @Operation(summary = "Create transaction", operationId = "createTransaction")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "Transaction created and returned",
                    content = @Content(
                            schema = @Schema(type = SchemaType.OBJECT, implementation = Transaction.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response createTransaction(CreateTransactionRequest request) {
        var transaction = transactionService.createTransaction(request.userId(), request.productId(), request.value(), request.amount());
        return Response.ok().entity(transaction).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Get Transaction by ID", operationId = "getTransaction")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "Transaction found and returned",
                    content = @Content(
                            schema = @Schema(type = SchemaType.OBJECT, implementation = Transaction.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response getTransaction(UUID id) {
        var transaction = transactionService.getTransaction(id);
        return Response.ok().entity(transaction).build();
    }

    @GET
    @Path("all")
    @Operation(summary = "Get all transactions", operationId = "getAllTransactions")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "All transactions returned",
                    content = @Content(
                            schema = @Schema(type = SchemaType.ARRAY, implementation = Transaction.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response getAllTransactions() {
        var transactions = transactionService.getAllTransactions();
        return Response.ok().entity(transactions).build();
    }

    @PUT
    @Transactional
    @Operation(summary = "Update Transaction", operationId = "updateTransaction")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "Transaction updated",
                    content = @Content(
                            schema = @Schema(type = SchemaType.OBJECT, implementation = Transaction.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response updateTransaction(UpdateTransactionRequest request) {
        var transaction = transactionService.updateTransaction(request.transaction());
        return Response.ok().entity(transaction).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Operation(summary = "Delete Transaction by ID", operationId = "deleteTransaction")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "Transaction deleted",
                    content = @Content(
                            schema = @Schema(type = SchemaType.DEFAULT),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response deleteTransaction(UUID id) {
        transactionService.deleteTransaction(id);
        return Response.ok().build();
    }

    @POST
    @Path("purchase")
    @Transactional
    @Operation(summary = "User purchases a product", operationId = "purchase")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "Product purchased and transaction created",
                    content = @Content(
                            schema = @Schema(type = SchemaType.OBJECT, implementation = Transaction.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response purchase(PurchaseRequest request) {
        var transaction = transactionService.purchase(request.userId(), request.productId(), request.value(), request.amount());
        return Response.ok().entity(transaction).build();
    }

    @POST
    @Path("getUserHistory")
    @Operation(summary = "Get transaction-history for user by ID", operationId = "getUserHistory")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "Transaction-history for user returned",
                    content = @Content(
                            schema = @Schema(type = SchemaType.ARRAY, implementation = Transaction.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response getUserHistory(GetUserHistoryRequest request) {
        var userHistory = transactionService.getUserHistory(request.userId());
        return Response.ok().entity(userHistory).build();
    }
}
