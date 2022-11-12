package net.sternstein.saft.api;

import net.sternstein.saft.domain.Purchase;
import net.sternstein.saft.model.dto.purchase.CreatePurchaseRequest;
import net.sternstein.saft.model.dto.purchase.UpdatePurchaseRequest;
import net.sternstein.saft.service.PurchaseService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("purchase")
@Consumes("application/json")
@Produces("application/json")
public class PurchaseRestApi implements PurchaseApi {

    @Inject
    PurchaseService purchaseService;

    @POST
    @Transactional
    @Operation(summary = "Create purchase", operationId = "createPurchase")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "Purchase created and returned",
                    content = @Content(
                            schema = @Schema(type = SchemaType.OBJECT, implementation = Purchase.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response createPurchase(CreatePurchaseRequest request) {
        var purchase = purchaseService.createPurchase(request.transactionId(), request.productId(), request.value(), request.amount());
        return Response.ok().entity(purchase).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Get Purchase by ID", operationId = "getPurchase")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "Purchase found and returned",
                    content = @Content(
                            schema = @Schema(type = SchemaType.OBJECT, implementation = Purchase.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response getPurchase(UUID id) {
        var purchase = purchaseService.getPurchase(id);
        return Response.ok().entity(purchase).build();
    }

    @GET
    @Path("all")
    @Operation(summary = "Get all purchases", operationId = "getAllPurchases")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "All purchases returned",
                    content = @Content(
                            schema = @Schema(type = SchemaType.ARRAY, implementation = Purchase.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response getAllPurchases() {
        var purchases = purchaseService.getAllPurchases();
        return Response.ok().entity(purchases).build();
    }

    @PUT
    @Transactional
    @Operation(summary = "Update Purchase", operationId = "updatePurchase")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "Purchase updated",
                    content = @Content(
                            schema = @Schema(type = SchemaType.OBJECT, implementation = Purchase.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response updatePurchase(UpdatePurchaseRequest request) {
        var purchase = purchaseService.updatePurchase(request.purchase());
        return Response.ok().entity(purchase).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Operation(summary = "Delete Purchase by ID", operationId = "deletePurchase")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "Purchase deleted",
                    content = @Content(
                            schema = @Schema(type = SchemaType.DEFAULT),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response deletePurchase(UUID id) {
        purchaseService.deletePurchase(id);
        return Response.ok().build();
    }
}
