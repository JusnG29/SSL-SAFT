package net.sternstein.saft.api;

import net.sternstein.saft.models.dtos.product.CreateProductRequest;
import net.sternstein.saft.models.dtos.product.DeleteProductRequest;
import net.sternstein.saft.models.dtos.product.UpdateProductRequest;
import net.sternstein.saft.services.ProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("product")
@Consumes("application/json")
@Produces("application/json")
public class ProductRestApi implements ProductApi {

    @Inject
    ProductService productService;

    @POST
    @Override
    public Response createProduct(CreateProductRequest request) {
        var product = productService.createProduct(request.name(), request.price());
        return Response.ok().entity(product).build();
    }

    @GET
    @Override
    public Response getProduct(UUID id) {
        var product = productService.getProduct(id);
        return Response.ok().entity(product).build();
    }

    @GET
    // TODO: check path ok?
    @Path("all")
    @Override
    public Response getAllProducts() {
        var products = productService.getAllProducts();
        return Response.ok().entity(products).build();
    }

    @PUT
    @Override
    public Response updateProduct(UpdateProductRequest request) {
        var product = productService.updateProduct(request.product());
        return Response.ok().entity(product).build();
    }

    @DELETE
    @Override
    public Response deleteProduct(UUID id) {
        boolean isRemoved = productService.deleteProduct(id);
        if(!isRemoved) {
            // TODO: do this the right way
            return Response.serverError().build();
        }
        return Response.ok().build();
    }
}
