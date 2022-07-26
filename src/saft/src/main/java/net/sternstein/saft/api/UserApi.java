package net.sternstein.saft.api;

import net.sternstein.saft.model.dto.user.CreateUserRequest;
import net.sternstein.saft.model.dto.user.UpdateUserRequest;

import javax.ws.rs.core.Response;

public interface UserApi {
    Response createUser(CreateUserRequest request);
    Response getUser(Long id);
    Response getAllUsers();
    Response updateUser(UpdateUserRequest request);
    Response deleteUser(Long id);
}
