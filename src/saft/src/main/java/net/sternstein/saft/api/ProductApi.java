package net.sternstein.saft.api;

import net.sternstein.saft.model.dto.product.CreateProductRequest;
import net.sternstein.saft.model.dto.product.DeleteProductRequest;
import net.sternstein.saft.model.dto.product.UpdateProductRequest;

import javax.ws.rs.core.Response;

public interface ProductApi {
    Response createProduct(CreateProductRequest request);
    Response getAllProducts();
    Response updateProduct(UpdateProductRequest request);
    Response deleteProduct(DeleteProductRequest request);
}
