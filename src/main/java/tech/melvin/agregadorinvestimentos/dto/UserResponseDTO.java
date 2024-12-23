package tech.melvin.agregadorinvestimentos.dto;

import java.time.Instant;
import java.util.UUID;

public record UserResponseDTO(UUID userId,
                              String username,
                              String email,
                              Instant creationTimestamp,
                              Instant updateTimestamp) {
}
