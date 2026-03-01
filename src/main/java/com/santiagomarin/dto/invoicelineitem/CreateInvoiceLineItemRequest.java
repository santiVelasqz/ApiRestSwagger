package com.santiagomarin.dto.invoicelineitem;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Request to create a line item")
public record CreateInvoiceLineItemRequest(

    @Schema(example = "1")
    @NotNull(message = "Product is required")
    Long productId,

    @Schema(example = "3")
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than 0")
    Integer quantity,

    @Schema(example = "5.00", description = "Discount percentage, 0 if no discount")
    @PositiveOrZero(message = "Discount cannot be negative")
    @Digits(integer = 3, fraction = 2, message = "Invalid discount format")
    BigDecimal discountPercent

) {}
