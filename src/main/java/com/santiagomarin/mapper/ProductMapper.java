package com.santiagomarin.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.santiagomarin.dto.product.CreateProductRequest;
import com.santiagomarin.dto.product.ProductResponse;
import com.santiagomarin.dto.product.UpdateProductRequest;
import com.santiagomarin.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	@Mapping(target = "id", ignore = true)
    @Mapping(target = "priceHistory", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "stockQuantity", ignore = true)
	@Mapping(target = "category", ignore = true) 
    Product toEntity(CreateProductRequest request);
	
	@Mapping(target = "currentPrice", expression = "java(product.getCurrentPrice())")
	@Mapping(target = "categoryName", source = "category.name")
    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponseList(List<Product> products);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "priceHistory", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "stockQuantity", ignore = true)
    @Mapping(target = "category", ignore = true) 
    void updateEntity(@MappingTarget Product product, UpdateProductRequest request);

}

