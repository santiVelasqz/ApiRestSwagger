package com.santiagomarin.dto.stockmovement;

import java.time.LocalDateTime;

import com.santiagomarin.entities.StockMovementReason;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Stock movement data")
public record StockMovementResponse(

    Long id,
    Long productId,
    String productName,
    Integer quantity,
    StockMovementReason reason,
    String notes,
    String createdBy,
    LocalDateTime createdAt

) {}
