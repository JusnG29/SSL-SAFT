package net.sternstein.saft.api;

import net.sternstein.saft.services.ProductService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("product")
@Consumes("application/json")
@Produces("application/json")
public class ProductRestApi implements ProductApi {

    @Inject
    ProductService productService;
}
