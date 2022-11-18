package net.sternstein.saft.api;

import net.sternstein.saft.domain.User;
import net.sternstein.saft.model.dto.user.BalanceRequest;
import net.sternstein.saft.model.dto.user.CreateUserRequest;
import net.sternstein.saft.model.dto.user.LoginRequest;
import net.sternstein.saft.model.dto.user.UpdateUserRequest;
import net.sternstein.saft.service.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.UUID;

@Path("user")
@Consumes("application/json")
@Produces("application/json")
public class UserRestApi implements UserApi {
    @Inject
    UserService userService;

    @POST
    @Transactional
    @Operation(summary = "Create user", operationId = "createUser")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "User was created and returned",
                    content = @Content(
                            schema = @Schema(type = SchemaType.OBJECT, implementation = User.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response createUser(CreateUserRequest request) {
        // TODO: do some validation

        var user = this.userService.createUser(request.couleurName(), request.passcode());

        return Response.ok().entity(user).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Get user by ID", operationId = "getUser")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "User was found and returned",
                    content = @Content(
                            schema = @Schema(type = SchemaType.OBJECT, implementation = User.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response getUser(UUID id) {
        var user = userService.getUser(id);
        return Response.ok().entity(user).build();
    }

    @GET
    @Path("all")
    @Operation(summary = "Get all users", operationId = "getAllUsers")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "All users returned",
                    content = @Content(
                            schema = @Schema(type = SchemaType.ARRAY, implementation = User.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response getAllUsers() {
        var users = userService.getAllUsers();

        return Response.ok().entity(users).build();
    }

    @PUT
    @Transactional
    @Operation(summary = "Update user", operationId = "updateUser")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "User was updated",
                    content = @Content(
                            schema = @Schema(type = SchemaType.OBJECT, implementation = User.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response updateUser(UpdateUserRequest request) {
        var user = userService.updateUser(request.user());
        return Response.ok().entity(user).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Operation(summary = "Delete user by ID", operationId = "deleteUser")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "User was deleted",
                    content = @Content(
                            schema = @Schema(type = SchemaType.DEFAULT),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response deleteUser(UUID id) {
        userService.deleteUser(id);
        return Response.ok().build();
    }

    @POST
    @Path("login")
    @Operation(summary = "Login for user", operationId = "login")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "User was logged in",
                    content = @Content(
                            schema = @Schema(type = SchemaType.OBJECT, implementation = User.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
    @Override
    public Response login(LoginRequest request) {
        boolean success = userService.login(request.id(), request.passcode());

        if(!success) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        var user = userService.getUser(request.id());

        return Response.ok(user).build();
    }

    @POST
    @Path("balance")
    @Operation(summary = "Get balance for user by ID", operationId = "balance")
    @APIResponses({
            @APIResponse(
                    name = "Success",
                    description = "User was found and balance returned",
                    content = @Content(
                            schema = @Schema(type = SchemaType.NUMBER, implementation = BigDecimal.class),
                            mediaType = MediaType.APPLICATION_JSON),
                    responseCode = "200"
            )})
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