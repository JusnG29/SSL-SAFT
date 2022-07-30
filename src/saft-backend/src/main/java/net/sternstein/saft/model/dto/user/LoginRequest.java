package net.sternstein.saft.model.dto.user;

import io.smallrye.common.constraint.NotNull;

import java.util.UUID;

public record LoginRequest(UUID id, String passcode) {
}
