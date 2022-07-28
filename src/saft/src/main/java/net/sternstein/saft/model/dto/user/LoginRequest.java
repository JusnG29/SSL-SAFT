package net.sternstein.saft.model.dto.user;

import java.util.UUID;

public record LoginRequest(UUID id, String passcode) {
}
