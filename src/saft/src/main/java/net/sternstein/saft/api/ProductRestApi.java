package net.sternstein.saft.api;

import net.sternstein.saft.model.dto.product.CreateProductRequest;
import net.sternstein.saft.model.dto.product.UpdateProductRequest;
import net.sternstein.saft.service.ProductService;

import javax.inject.Inject;
import javax.transaction.Transactional;
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
    @Transactional
    @Override
    public Response createProduct(CreateProductRequest request) {
        var product = productService.createProduct(request.name(), request.price());
        return Response.ok().entity(product).build();
    }

    @GET
    @Path("{id}")
    @Override
    public Response getProduct(UUID id) {
        var product = productService.getProduct(id);
        return Response.ok().entity(product).build();
    }

    @GET
    @Path("all")
    @Override
    public Response getAllProducts() {
        var products = productService.getAllProducts();
        return Response.ok().entity(products).build();
    }

    @PUT
    @Transactional
    @Override
    public Response updateProduct(UpdateProductRequest request) {
        var product = productService.updateProduct(request.product());
        return Response.ok().entity(product).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
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
