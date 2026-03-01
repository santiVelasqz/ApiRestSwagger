package com.santiagomarin.dto.invoice;

import java.time.LocalDate;

import com.santiagomarin.entities.InvoiceStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(description="Request to update a invoice")
public record UpdateInvoiceRequest(
		
		@Size(max = 500)
	    String notes,
	    @Schema(example = "2024-02-15")
	    LocalDate dueDate,
	    @Schema(example = "ISSUED")
	    InvoiceStatus status
		
		) {

}
