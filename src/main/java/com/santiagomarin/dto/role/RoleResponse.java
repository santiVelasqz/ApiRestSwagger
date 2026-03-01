package com.santiagomarin.dto.role;

import com.santiagomarin.entities.RoleName;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Role data returned by the API")
public record RoleResponse(

    @Schema(example = "1")
    Long id,

    @Schema(example = "ADMIN")
    RoleName name,

    @Schema(example = "Full system access")
    String description

) {}
