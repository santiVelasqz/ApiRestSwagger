package com.santiagomarin.dto.client;

import java.time.LocalDateTime;

import com.santiagomarin.entities.ClientStatus;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Client data")
public record ClientResponse(
		Long id,
	    String fullName,
	    String email,
	    String taxId,
	    String address,
	    String phone,
	    ClientStatus status,
	    LocalDateTime createdAt
	    ) {}
