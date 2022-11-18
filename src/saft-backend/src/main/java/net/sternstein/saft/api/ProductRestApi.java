package net.sternstein.saft.api;

import net.sternstein.saft.domain.Product;
import net.sternstein.saft.model.dto.product.CreateProductRequest;
import net.sternstein.saft.model.dto.product.UpdateProductRequest;
import net.sternstein.saft.services.ProductService;
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

@Path("product")
@Consumes("application/json")
@Produces("application/json")
public class ProductRestApi implements ProductApi {

    @Inject
    ProductService productService;

    @POST
    @Transactional
    @Operation(summary = "Create product", operationId = "createProduct")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "Product created and returned",
                    content = @Content(
                            schema = @Schema(type = SchemaType.OBJECT, implementation = Product.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response createProduct(CreateProductRequest request) {
        var product = productService.createProduct(
                request.name(),
                request.price(),
                request.colorCode(),
                request.description()
        );
        return Response.ok().entity(product).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Get product by ID", operationId = "getProduct")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "Product found and returned",
                    content = @Content(
                            schema = @Schema(type = SchemaType.OBJECT, implementation = Product.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response getProduct(UUID id) {
        var product = productService.getProduct(id);
        return Response.ok().entity(product).build();
    }

    @GET
    @Path("all")
    @Operation(summary = "Get all products", operationId = "getAllProducts")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "All products returned",
                    content = @Content(
                            schema = @Schema(type = SchemaType.ARRAY, implementation = Product.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response getAllProducts() {
        var products = productService.getAllProducts();
        return Response.ok().entity(products).build();
    }

    @PUT
    @Transactional
    @Operation(summary = "Update product", operationId = "updateProduct")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "Product updated and returned",
                    content = @Content(
                            schema = @Schema(type = SchemaType.OBJECT, implementation = Product.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response updateProduct(UpdateProductRequest request) {
        var product = productService.updateProduct(request.product());
        return Response.ok().entity(product).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Operation(summary = "Delete product by ID", operationId = "deleteProduct")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "Product deleted",
                    content = @Content(
                            schema = @Schema(type = SchemaType.DEFAULT),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response deleteProduct(UUID id) {
        productService.deleteProduct(id);
        return Response.ok().build();
    }
}
