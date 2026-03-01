package com.santiagomarin.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(description = "Request to update a role")
public record UpdateRoleRequest(

    @Schema(example = "Full system access updated")
    @Size(max = 300)
    String description

) {}
