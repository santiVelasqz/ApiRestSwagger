package com.santiagomarin.dto.user;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Request to create a user")
public record CreateUserRequest(

    @Schema(example = "juan.perez")
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 chars")
    String username,

    @Schema(example = "juan@email.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    @Size(max = 100)
    String email,

    @Schema(example = "SecurePass123!")
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 chars")
    String password,

    @Schema(example = "Juan Pérez")
    @NotBlank(message = "Full name is required")
    @Size(max = 200)
    String fullName,

    @Schema(example = "[1, 2]")
    @NotNull(message = "At least one role is required")
    @Size(min = 1, message = "User must have at least one role")
    Set<Long> roleIds

) {}