package net.sternstein.saft.api;

import net.sternstein.saft.models.dtos.CreateUserRequest;

import javax.ws.rs.core.Response;

public interface UserApi {
    Response createUser(CreateUserRequest createUserRequest);
    Response getAllUsers();
}
