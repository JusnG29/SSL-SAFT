package net.sternstein.saft.api;

import net.sternstein.saft.models.dtos.user.CreateUserRequest;
import net.sternstein.saft.models.dtos.user.DeleteUserRequest;
import net.sternstein.saft.models.dtos.user.UpdateUserRequest;

import javax.ws.rs.core.Response;
import java.util.UUID;

public interface UserApi {
    Response createUser(CreateUserRequest request);
    Response getUser(UUID id);
    Response getAllUsers();
    Response updateUser(UpdateUserRequest request);
    Response deleteUser(UUID id);
}
