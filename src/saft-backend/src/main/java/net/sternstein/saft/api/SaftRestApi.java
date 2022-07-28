package net.sternstein.saft.api;

<<<<<<< HEAD:src/saft-backend/src/main/java/net/sternstein/saft/api/SaftRestApi.java
import net.sternstein.saft.models.dtos.user.CreateUserRequest;
import net.sternstein.saft.models.dtos.user.DeleteUserRequest;
import net.sternstein.saft.models.dtos.user.UpdateUserRequest;
import net.sternstein.saft.services.UserService;
=======
import net.sternstein.saft.model.dto.user.BalanceRequest;
import net.sternstein.saft.model.dto.user.CreateUserRequest;
import net.sternstein.saft.model.dto.user.LoginRequest;
import net.sternstein.saft.model.dto.user.UpdateUserRequest;
import net.sternstein.saft.service.UserService;
>>>>>>> 4ffa25b (added getBalance endpoint + functionality):src/saft/src/main/java/net/sternstein/saft/api/UserRestApi.java

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("user")
@Consumes("application/json")
@Produces("application/json")
public class UserRestApi implements UserApi {
    @Inject
    UserService userService;

    @POST
    @Transactional
    @Override
    public Response createUser(CreateUserRequest request) {
        // TODO: do some validation

        var user = this.userService.createUser(request.couleurName(), request.passcode());

        return Response.ok().entity(user).build();
    }

    @GET
    @Path("{id}")
    @Override
    public Response getUser(UUID id) {
        var user = userService.getUser(id);
        return Response.ok().entity(user).build();
    }

    @GET
    @Path("all")
    @Override
    public Response getAllUsers() {
        var users = userService.getAllUsers();

        return Response.ok().entity(users).build();
    }

    @PUT
    @Transactional
    @Override
    public Response updateUser(UpdateUserRequest request) {
        var user = userService.updateUser(request.user());
        return Response.ok().entity(user).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Override
    public Response deleteUser(UUID id) {
        userService.deleteUser(id);
        return Response.ok().build();
    }

    @POST
    @Path("login")
    @Override
    public Response login(LoginRequest request) {
        boolean success = userService.login(request.id(), request.passcode());

        if(!success) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok().build();
    }

    @POST
    @Path("balance")
    @Override
    public Response balance(BalanceRequest request) {
        var balance = userService.getBalance(request.id());
        return Response.ok().entity(balance).build();
    }

    //TODO: remove! only for local dev
    @POST
    @Path("init")
    @Transactional
    @Override
    public Response init() {
        userService.init();
        return Response.ok().build();
    }
}
