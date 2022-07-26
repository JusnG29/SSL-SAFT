package net.sternstein.saft.api;

import net.sternstein.saft.model.dto.product.CreateProductRequest;
import net.sternstein.saft.model.dto.product.DeleteProductRequest;
import net.sternstein.saft.model.dto.product.UpdateProductRequest;
import net.sternstein.saft.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("product")
@Consumes("application/json")
@Produces("application/json")
public class ProductRestApi implements ProductApi {

    @Inject
    ProductService productService;

    @POST
    @Override
    public Response createProduct(CreateProductRequest request) {
        productService.createProduct();
        return null;
    }

    @GET
    @Override
    public Response getAllProducts() {
        productService.getAllProducts();
        return null;
    }

    @PUT
    @Override
    public Response updateProduct(UpdateProductRequest request) {
        productService.updateProduct();
        return null;
    }

    @DELETE
    @Override
    public Response deleteProduct(DeleteProductRequest request) {
        productService.deleteProduct();
        return null;
    }
}
