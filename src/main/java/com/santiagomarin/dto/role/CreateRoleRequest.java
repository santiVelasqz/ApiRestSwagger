package com.santiagomarin.dto.role;

import com.santiagomarin.entities.RoleName;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Request to create a role")
public record CreateRoleRequest(

    @Schema(example = "ADMIN")
    @NotNull(message = "Role name is required")
    RoleName name,

    @Schema(example = "Full system access")
    @Size(max = 300)
    String description

) {}
