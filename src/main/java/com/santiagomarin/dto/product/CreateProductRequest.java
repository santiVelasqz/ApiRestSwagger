package com.santiagomarin.dto.product;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request to create a product")
public record CreateProductRequest(
		
		@Schema(example = "Macbook Air")
	    @NotBlank(message = "Name is required")
		@Size(min = 2, max = 200, message = "Name must be between 2 and 200 chars")
		String name,
		
		@Schema(example = "T-Shirt size L")
	    @Size(max = 500, message = "Description must not exceed 500 chars")
	    String description,

	    @Schema(example = "T-shirt-blue-L")
		@NotBlank(message="Sku is required")
	    @Size(max = 50, message = "SKU must not exceed 50 chars")
	    String sku,
	    
	    @Schema(example = "1", description = "Optional category")
		Long categoryId
		) {

}
