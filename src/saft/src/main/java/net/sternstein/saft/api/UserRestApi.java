package net.sternstein.saft.api;

import net.sternstein.saft.models.dtos.user.CreateUserRequest;
import net.sternstein.saft.models.dtos.user.DeleteUserRequest;
import net.sternstein.saft.models.dtos.user.UpdateUserRequest;
import net.sternstein.saft.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

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

    @Override
    @GET
    public Response getAllUsers() {
        var users = userService.getAllUsers();

        return Response.ok().entity(users).build();
    }

    @PUT
    @Override
    public Response updateUser(UpdateUserRequest request) {
        userService.updateUser();
        return null;
    }

    @DELETE
    @Override
    public Response deleteUser(DeleteUserRequest request) {
        userService.deleteUser();
        return null;
    }
}
