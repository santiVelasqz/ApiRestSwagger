package com.santiagomarin.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Schema(description="Request to update a client")
public record UpdateClientRequest(
		@Size(max = 200)
	    String fullName,

	    @Email(message = "Email format is invalid")
	    @Size(max = 100)
	    String email,

	    @Size(max = 20)
	    String taxId,

	    @Size(max = 300)
	    String address,

	    @Size(max = 20)
	    String phone
		) {}
