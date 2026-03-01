package com.santiagomarin.dto.invoicelineitem;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Request to update a line item")
public record UpdateInvoiceLineItemRequest(

    @Schema(example = "5")
    @Positive(message = "Quantity must be greater than 0")
    Integer quantity,

    @Schema(example = "10.00")
    @PositiveOrZero(message = "Discount cannot be negative")
    @Digits(integer = 3, fraction = 2, message = "Invalid discount format")
    BigDecimal discountPercent

) {}
