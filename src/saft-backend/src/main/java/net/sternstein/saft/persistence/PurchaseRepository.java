package net.sternstein.saft.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import net.sternstein.saft.domain.Purchase;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class PurchaseRepository implements PanacheRepositoryBase<Purchase, UUID> {
}
