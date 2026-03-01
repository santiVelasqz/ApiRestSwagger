package com.santiagomarin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiagomarin.entities.InvoiceLineItem;

public interface InvoiceLineItemRepository extends JpaRepository<InvoiceLineItem, Long>{
	
	List<InvoiceLineItem> findByInvoiceId(Long invoiceId);

}
