package com.santiagomarin.dto.category;

import java.time.LocalDateTime;

import com.santiagomarin.entities.CategoryStatus;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Category data returned by the API")
public record CategoryResponse(

    @Schema(example = "1")
    Long id,

    @Schema(example = "Ropa")
    String name,

    @Schema(example = "Ropa y accesorios de moda")
    String description,

    @Schema(example = "ACTIVE")
    CategoryStatus status,

    @Schema(example = "2024-01-15T10:30:00")
    LocalDateTime createdAt

) {}
