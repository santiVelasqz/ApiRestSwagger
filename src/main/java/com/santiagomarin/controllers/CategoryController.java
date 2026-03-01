package com.santiagomarin.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santiagomarin.dto.category.CategoryResponse;
import com.santiagomarin.dto.category.CreateCategoryRequest;
import com.santiagomarin.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(name="Categories", description="Categories management API")
public class CategoryController {
	
	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
	private CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping
	@Operation(summary="", description="", responses = {
			@ApiResponse(
					responseCode = "200",
					description = "Returns a list of all categories",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = CategoryResponse.class)
					)	
			)
	})
	public ResponseEntity<List<CategoryResponse>> showCategories(){
		log.info("GET /api/v1/categories - Fetching all categories"); 
		List<CategoryResponse> categories = categoryService.findAll();
		log.info("GET /api/v1/categories - Returned {} categories", categories.size());
		return ResponseEntity.ok(categories);
	}
	
	
	
	
	@PostMapping
	@Operation(summary="Create a new Category", description="Creates a new category and returns the created category", responses = {
			@ApiResponse(
					responseCode = "201",
					description = "Category created successfully",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = CategoryResponse.class)
					)
			)		
	})
	public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest request){
		log.info("POST /api/v1/categories - Creating Category: {} {}", request.name(), request.description());
		CategoryResponse category = categoryService.createCategory(request);
		log.info("POST /api/v1/categories - Category created with ID: {}", category.id());
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
	
	

}
