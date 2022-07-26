package net.sternstein.saft.model.dto.user;

import net.sternstein.saft.domain.User;

// TODO: check serialization stuff
public record UpdateUserRequest(User user) {
}
