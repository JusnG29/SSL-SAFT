package net.sternstein.saft.api;

import net.sternstein.saft.models.dtos.product.CreateProductRequest;
import net.sternstein.saft.models.dtos.product.UpdateProductRequest;

import javax.ws.rs.core.Response;

public interface ProductApi {
    Response createProduct(CreateProductRequest request);
    Response getProduct(Long id);
    Response getAllProducts();
    Response updateProduct(UpdateProductRequest request);
    Response deleteProduct(Long id);
}
