package com.santiagomarin.dto.invoice;

import java.time.LocalDate;
import java.util.List;

import com.santiagomarin.dto.invoicelineitem.CreateInvoiceLineItemRequest;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Request to create a invoice")
public record CreateInvoiceRequest(
		
		@Schema(example = "1")
	    @NotNull(message = "Client is required")
	    Long clientId,

	    @Schema(example = "2024-02-15")
	    LocalDate dueDate,

	    @Schema(example = "Pago a 30 días")
	    @Size(max = 500, message = "Notes must not exceed 500 chars")
	    String notes,

	    @NotNull(message = "Line items are required")
	    @Size(min = 1, message = "Invoice must have at least one line item")
	    List<CreateInvoiceLineItemRequest> lineItems
		
		) {

}
