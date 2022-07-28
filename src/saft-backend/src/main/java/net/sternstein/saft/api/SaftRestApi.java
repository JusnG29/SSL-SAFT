package net.sternstein.saft.api;

import net.sternstein.saft.models.dtos.user.CreateUserRequest;
import net.sternstein.saft.models.dtos.user.DeleteUserRequest;
import net.sternstein.saft.models.dtos.user.UpdateUserRequest;
import net.sternstein.saft.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("user")
@Consumes("application/json")
@Produces("application/json")
public class UserRestApi implements UserApi {
    @Inject
    UserService userService;

    @Override
    @POST
    public Response createUser(CreateUserRequest request) {
        // TODO: do some validation

        var user = this.userService.createUser(request.couleurName(), request.passcode());

        return Response.ok().entity(user).build();
    }

    @GET
    @Override
    public Response getUser(UUID id) {
        var user = userService.getUser(id);
        return Response.ok().entity(user).build();
    }

    @GET
    // TODO: check Path ok?
    @Path("all")
    @Override
    public Response getAllUsers() {
        var users = userService.getAllUsers();

        return Response.ok().entity(users).build();
    }

    @PUT
    @Override
    public Response updateUser(UpdateUserRequest request) {
        var user = userService.updateUser(request.user());
        return Response.ok().entity(user).build();
    }

    @DELETE
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
