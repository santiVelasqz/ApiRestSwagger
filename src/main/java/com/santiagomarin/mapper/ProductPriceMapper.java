package com.santiagomarin.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.santiagomarin.dto.productprice.CreateProductPriceRequest;
import com.santiagomarin.dto.productprice.ProductPriceResponse;
import com.santiagomarin.entities.ProductPrice;

@Mapper(componentModel = "spring")
public interface ProductPriceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)      // se asigna en el service
    @Mapping(target = "effectiveDate", ignore = true) // se asigna en el service como now()
    @Mapping(target = "endDate", ignore = true)       // se asigna en el service al cerrar precio anterior
    ProductPrice toEntity(CreateProductPriceRequest request);

    ProductPriceResponse toResponse(ProductPrice productPrice);

    List<ProductPriceResponse> toResponseList(List<ProductPrice> prices);
}
