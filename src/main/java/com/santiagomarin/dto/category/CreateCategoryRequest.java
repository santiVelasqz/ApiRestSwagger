package com.santiagomarin.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request to create a category")
public record CreateCategoryRequest(

    @Schema(example = "Ropa")
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 chars")
    String name,

    @Schema(example = "Ropa y accesorios de moda")
    @Size(max = 300, message = "Description must not exceed 300 chars")
    String description

) {}
