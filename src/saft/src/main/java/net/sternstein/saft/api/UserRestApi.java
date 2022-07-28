package net.sternstein.saft.api;

import net.sternstein.saft.model.dto.user.CreateUserRequest;
import net.sternstein.saft.model.dto.user.UpdateUserRequest;
import net.sternstein.saft.service.UserService;

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
        boolean isRemoved = userService.deleteUser(id);
        if(!isRemoved) {
            // TODO: do this the right way
            return Response.serverError().build();
        }
        return Response.ok().build();
    }
}
