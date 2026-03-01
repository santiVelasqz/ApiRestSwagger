package com.santiagomarin.dto.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.santiagomarin.dto.invoicelineitem.InvoiceLineItemResponse;
import com.santiagomarin.entities.InvoiceStatus;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Invoice data")
public record InvoiceResponse(

    @Schema(example = "1")
    Long id,

    @Schema(example = "INV-2024-00001")
    String invoiceNumber,

    @Schema(example = "Juan Pérez")
    String clientName,           // viene de client.fullName

    @Schema(example = "ISSUED")
    InvoiceStatus status,

    @Schema(example = "2024-01-15T10:30:00")
    LocalDateTime issueDate,

    @Schema(example = "2024-02-15")
    LocalDate dueDate,

    List<InvoiceLineItemResponse> lineItems,

    @Schema(example = "100.00")
    BigDecimal subtotal,

    @Schema(example = "21.00")
    BigDecimal taxAmount,

    @Schema(example = "121.00")
    BigDecimal total,

    @Schema(example = "Pago a 30 días")
    String notes,

    @Schema(example = "2024-01-15T10:30:00")
    LocalDateTime createdAt

) {}
