package com.santiagomarin.dto.stockmovement;

import com.santiagomarin.entities.StockMovementReason;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Request to adjust product stock")
public record AdjustStockRequest(

    @Schema(example = "50", description = "Positive = entry, negative = exit")
    @NotNull(message = "Quantity is required")
    Integer quantity,

    @Schema(example = "PURCHASE")
    @NotNull(message = "Reason is required")
    StockMovementReason reason,

    @Schema(example = "Compra a proveedor XYZ")
    @Size(max = 300)
    String notes

) {}
