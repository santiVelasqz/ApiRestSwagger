package com.santiagomarin.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.santiagomarin.dto.invoicelineitem.CreateInvoiceLineItemRequest;
import com.santiagomarin.dto.invoicelineitem.InvoiceLineItemResponse;
import com.santiagomarin.dto.invoicelineitem.UpdateInvoiceLineItemRequest;
import com.santiagomarin.entities.InvoiceLineItem;

@Mapper(componentModel = "spring")
public interface InvoiceLineItemMapper {

	@Mapping(target = "id", ignore = true)
    @Mapping(target = "invoice", ignore = true)     // se asigna en el service
    @Mapping(target = "lineTotal", ignore = true)   // se calcula con @PrePersist
    @Mapping(target = "product", ignore = true)     // se busca en BBDD por productId
    InvoiceLineItem toEntity(CreateInvoiceLineItemRequest request);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "invoiceId", source = "invoice.id")
    InvoiceLineItemResponse toResponse(InvoiceLineItem invoiceLineItem);

    List<InvoiceLineItemResponse> toResponseList(List<InvoiceLineItem> invoiceLineItems);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "invoice", ignore = true)
    @Mapping(target = "lineTotal", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "unitPrice", ignore = true)
    void updateEntity(@MappingTarget InvoiceLineItem invoiceLineItem, UpdateInvoiceLineItemRequest request);
}
