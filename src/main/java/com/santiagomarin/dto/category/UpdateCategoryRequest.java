package com.santiagomarin.dto.category;

import com.santiagomarin.entities.CategoryStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(description = "Request to update a category")
public record UpdateCategoryRequest(

    @Schema(example = "Ropa y Moda")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 chars")
    String name,

    @Schema(example = "Ropa, calzado y accesorios")
    @Size(max = 300)
    String description,

    @Schema(example = "INACTIVE")
    CategoryStatus status

) {}
