package com.santiagomarin.dto.user;

import java.time.LocalDateTime;
import java.util.Set;

import com.santiagomarin.dto.role.RoleResponse;
import com.santiagomarin.entities.UserStatus;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User data returned by the API")
public record UserResponse(

    @Schema(example = "1")
    Long id,

    @Schema(example = "juan.perez")
    String username,

    @Schema(example = "juan@email.com")
    String email,

    @Schema(example = "Juan Pérez")
    String fullName,

    @Schema(example = "ACTIVE")
    UserStatus status,

    Set<RoleResponse> roles,

    @Schema(example = "2024-01-15T10:30:00")
    LocalDateTime createdAt

) {}