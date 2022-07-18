package net.sternstein.saft.api;

import net.sternstein.saft.models.dtos.CreateUserRequest;
import net.sternstein.saft.services.UserService;

import javax.annotation.Resource;
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
    public Response createUser(CreateUserRequest createUserRequest) {
        // TODO: do some validation

        var user = this.userService.createUser(createUserRequest.couleurName(), createUserRequest.passcode());

        return Response.ok().entity(user).build();
    }

    @Override
    @GET
    public Response getAllUsers() {
        var users = userService.getAllUsers();

        return Response.ok().entity(users).build();
    }
}
