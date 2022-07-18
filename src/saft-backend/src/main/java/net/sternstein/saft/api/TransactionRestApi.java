package net.sternstein.saft.api;

import net.sternstein.saft.services.TransactionService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("transaction")
@Consumes("application/json")
@Produces("application/json")
public class TransactionRestApi implements TransactionApi {

    @Inject
    TransactionService transactionService;
}
