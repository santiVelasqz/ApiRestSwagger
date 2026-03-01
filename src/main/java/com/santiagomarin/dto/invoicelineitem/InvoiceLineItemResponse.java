package com.santiagomarin.dto.invoicelineitem;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Line item data returned by the API")
public record InvoiceLineItemResponse(

    @Schema(example = "1")
    Long id,

    @Schema(example = "1")
    Long invoiceId,

    @Schema(example = "1")
    Long productId,

    @Schema(example = "Camiseta Roja")
    String productName,

    @Schema(example = "29.99", description = "Price at the moment of sale")
    BigDecimal unitPrice,

    @Schema(example = "3")
    Integer quantity,

    @Schema(example = "5.00")
    BigDecimal discountPercent,

    @Schema(example = "85.47")
    BigDecimal lineTotal

) {}
