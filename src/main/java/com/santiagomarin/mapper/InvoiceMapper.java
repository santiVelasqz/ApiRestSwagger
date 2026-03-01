package com.santiagomarin.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.santiagomarin.dto.invoice.CreateInvoiceRequest;
import com.santiagomarin.dto.invoice.InvoiceResponse;
import com.santiagomarin.dto.invoice.UpdateInvoiceRequest;
import com.santiagomarin.entities.Invoice;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "invoiceNumber", ignore = true)  //  se genera en el service
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "total", ignore = true)
	@Mapping(target = "subtotal", ignore = true)
	@Mapping(target = "taxAmount", ignore = true)
	@Mapping(target = "lineItems", ignore = true)
	@Mapping(target = "status", ignore = true)         //  se asigna como DRAFT en el service
	@Mapping(target = "client", ignore = true)   // se gestiona aparte
    Invoice toEntity(CreateInvoiceRequest request);

    @Mapping(target = "clientName", source = "client.fullName")
    InvoiceResponse toResponse(Invoice invoice);

    List<InvoiceResponse> toResponseList(List<Invoice> invoices);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "invoiceNumber", ignore = true)  
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "subtotal", ignore = true)
    @Mapping(target = "taxAmount", ignore = true)
    @Mapping(target = "lineItems", ignore = true)
    @Mapping(target = "client", ignore = true)         
    @Mapping(target = "issueDate", ignore = true)  
    void updateEntity(@MappingTarget Invoice invoice, UpdateInvoiceRequest request);

}
