package com.santiagomarin.dto.user;

import com.santiagomarin.entities.UserStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Schema(description = "Request to update a user")
public record UpdateUserRequest(

    @Schema(example = "juan@newemail.com")
    @Email(message = "Email format is invalid")
    @Size(max = 100)
    String email,

    @Schema(example = "Juan Manuel Pérez")
    @Size(max = 200)
    String fullName,

    @Schema(example = "INACTIVE")
    UserStatus status

) {}