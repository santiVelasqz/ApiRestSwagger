package com.santiagomarin.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request to create a client")
public record CreateClientRequest(

	    @Schema(example = "Santi marin")
	    @NotBlank(message = "Full name is required")
	    @Size(max = 200, message = "Full name must not exceed 200 chars")
	    String fullName,

	    @Schema(example = "santi@email.com")
	    @NotBlank(message = "Email is required")
	    @Email(message = "Email format is invalid")
	    @Size(max = 100)
	    String email,

	    @Schema(example = "12345678A")
	    @Size(max = 20, message = "Tax ID must not exceed 20 chars")
	    String taxId,

	    @Schema(example = "Calle Mayor 1, Madrid")
	    @Size(max = 300)
	    String address,

	    @Schema(example = "+34600000000")
	    @Size(max = 20)
	    String phone

	) {}
