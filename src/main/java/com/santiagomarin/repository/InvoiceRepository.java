package com.santiagomarin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiagomarin.entities.Invoice;
import com.santiagomarin.entities.InvoiceStatus;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
	
	Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
    List<Invoice> findByClientId(Long clientId);
    List<Invoice> findByStatus(InvoiceStatus status);

}
