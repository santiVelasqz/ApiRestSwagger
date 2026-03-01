package com.santiagomarin.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.santiagomarin.dto.stockmovement.AdjustStockRequest;
import com.santiagomarin.dto.stockmovement.StockMovementResponse;
import com.santiagomarin.entities.StockMovement;

@Mapper(componentModel = "spring")
public interface StockMovementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)   // se asigna en el service
    @Mapping(target = "createdAt", ignore = true) // lo pone Hibernate
    @Mapping(target = "createdBy", ignore = true) // se asigna en el service
    StockMovement toEntity(AdjustStockRequest request);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    StockMovementResponse toResponse(StockMovement stockMovement);

    List<StockMovementResponse> toResponseList(List<StockMovement> stockMovements);
}
