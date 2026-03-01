package com.santiagomarin.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="invoice_line_item")
public class InvoiceLineItem {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "line_item_id")
	    private Long id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "invoice_id", nullable = false)
	    private Invoice invoice;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "product_id", nullable = false)
	    private Product product;

	    @Column(nullable = false, precision = 10, scale = 2)
	    private BigDecimal unitPrice; // Precio SNAPSHOT en el momento de venta

	    @Column(nullable = false)
	    private Integer quantity;

	    @Column(name = "discount_percent", precision = 5, scale = 2)
	    private BigDecimal discountPercent = BigDecimal.ZERO;

	    @Column(name = "line_total", precision = 12, scale = 2, nullable = false)
	    private BigDecimal lineTotal;
	    
	    public InvoiceLineItem() {}

		public InvoiceLineItem(Long id, Invoice invoice, Product product, BigDecimal unitPrice, Integer quantity,
				BigDecimal discountPercent, BigDecimal lineTotal) {
			super();
			this.id = id;
			this.invoice = invoice;
			this.product = product;
			this.unitPrice = unitPrice;
			this.quantity = quantity;
			this.discountPercent = discountPercent;
			this.lineTotal = lineTotal;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Invoice getInvoice() {
			return invoice;
		}

		public void setInvoice(Invoice invoice) {
			this.invoice = invoice;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public BigDecimal getUnitPrice() {
			return unitPrice;
		}

		public void setUnitPrice(BigDecimal unitPrice) {
			this.unitPrice = unitPrice;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public BigDecimal getDiscountPercent() {
			return discountPercent;
		}

		public void setDiscountPercent(BigDecimal discountPercent) {
			this.discountPercent = discountPercent;
		}

		public BigDecimal getLineTotal() {
			return lineTotal;
		}

		public void setLineTotal(BigDecimal lineTotal) {
			this.lineTotal = lineTotal;
		}
	    
		@PrePersist
	    @PreUpdate
	    public void calculateLineTotal() {
	        BigDecimal base = unitPrice.multiply(BigDecimal.valueOf(quantity));
	        BigDecimal discount = base.multiply(discountPercent).divide(BigDecimal.valueOf(100));
	        this.lineTotal = base.subtract(discount);
	    }
	    
	

}
