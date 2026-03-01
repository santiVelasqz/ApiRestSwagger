package com.santiagomarin.dto.productprice;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Request to set a new product price")
public record CreateProductPriceRequest(

    @Schema(example = "39.99")
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Invalid price format")
    BigDecimal price,

    @Schema(example = "admin")
    @NotBlank(message = "Created by is required")
    String createdBy

) {}
