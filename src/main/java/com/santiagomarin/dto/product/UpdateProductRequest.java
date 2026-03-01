package com.santiagomarin.dto.product;

import com.santiagomarin.entities.ProductStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(description="Request to update a product")
public record UpdateProductRequest(
		
		@Schema(example = "Macbook Air")
	    @Size(min = 2, max = 200, message = "Name must be between 2 and 200 chars")
		String name,
		
		@Schema(example = "T-Shirt size L")
	    @Size(max = 500, message = "Description must not exceed 500 chars")
	    String description,
	    
	    @Schema(example = "ACTIVE")
	    ProductStatus status,

	    @Schema(example = "T-shirt-blue-L")
	    @Size(max = 50, message = "SKU must not exceed 50 chars")
	    String sku,
	    
	    @Schema(example = "2")
		Long categoryId
		) {
	
	

}
