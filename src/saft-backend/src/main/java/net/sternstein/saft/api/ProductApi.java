package net.sternstein.saft.api;

import net.sternstein.saft.models.dtos.product.CreateProductRequest;
import net.sternstein.saft.models.dtos.product.UpdateProductRequest;

import javax.ws.rs.core.Response;
import java.util.UUID;

public interface ProductApi {
    Response createProduct(CreateProductRequest request);
    Response getProduct(UUID id);
    Response getAllProducts();
    Response updateProduct(UpdateProductRequest request);
    Response deleteProduct(UUID id);
}
