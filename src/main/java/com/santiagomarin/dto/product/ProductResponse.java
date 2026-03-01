package com.santiagomarin.dto.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.santiagomarin.entities.ProductStatus;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Product data")
public record ProductResponse(

    @Schema(example = "1")
    Long id,

    @Schema(example = "Black Trousers")
    String name,

    @Schema(example = "T-shirt size M")
    String description,

    @Schema(example = "TSHIRT-RED-M")
    String sku,
    
    @Schema(example = "Ropa")
    String categoryName,

    @Schema(example = "29.99")
    BigDecimal currentPrice,

    @Schema(example = "100")
    Integer stockQuantity,

    @Schema(example = "ACTIVE")
    ProductStatus status,

    @Schema(example = "2024-01-15T10:30:00")
    LocalDateTime createdAt

) {}
