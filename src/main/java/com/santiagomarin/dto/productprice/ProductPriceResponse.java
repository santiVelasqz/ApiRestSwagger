package com.santiagomarin.dto.productprice;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Product price history entry")
public record ProductPriceResponse(

    @Schema(example = "1")
    Long id,

    @Schema(example = "39.99")
    BigDecimal price,

    @Schema(example = "2024-03-01T10:00:00")
    LocalDateTime effectiveDate,

    @Schema(description = "Null means this is the current active price")
    LocalDateTime endDate,

    @Schema(example = "admin")
    String createdBy

) {}
