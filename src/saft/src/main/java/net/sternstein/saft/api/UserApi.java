package net.sternstein.saft.api;

import net.sternstein.saft.model.dto.user.CreateUserRequest;
import net.sternstein.saft.model.dto.user.UpdateUserRequest;

import javax.ws.rs.core.Response;
import java.util.UUID;

public interface UserApi {
    Response createUser(CreateUserRequest request);
    Response getUser(UUID id);
    Response getAllUsers();
    Response updateUser(UpdateUserRequest request);
    Response deleteUser(UUID id);
}
