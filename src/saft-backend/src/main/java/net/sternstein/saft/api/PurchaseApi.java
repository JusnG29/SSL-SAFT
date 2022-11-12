package net.sternstein.saft.api;

import net.sternstein.saft.model.dto.purchase.CreatePurchaseRequest;
import net.sternstein.saft.model.dto.purchase.UpdatePurchaseRequest;

import javax.ws.rs.core.Response;
import java.util.UUID;

public interface PurchaseApi {
    Response createPurchase(CreatePurchaseRequest request);
    Response getPurchase(UUID id);
    Response getAllPurchases();
    Response updatePurchase(UpdatePurchaseRequest request);
    Response deletePurchase(UUID id);
}
