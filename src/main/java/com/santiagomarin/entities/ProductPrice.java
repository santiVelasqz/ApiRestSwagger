package com.santiagomarin.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_price")
public class ProductPrice {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "price_id")
	    private Long id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "product_id", nullable = false)
	    private Product product;

	    @Column(nullable = false, precision = 10, scale = 2)
	    private BigDecimal price;

	    @Column(name = "effective_date", nullable = false)
	    private LocalDateTime effectiveDate;

	    @Column(name = "end_date") // null = precio vigente
	    private LocalDateTime endDate;

	    @Column(name = "created_by")
	    private String createdBy;
	    
	    public ProductPrice() {}

		public ProductPrice(Long id, Product product, BigDecimal price, LocalDateTime effectiveDate,
				LocalDateTime endDate, String createdBy) {
			super();
			this.id = id;
			this.product = product;
			this.price = price;
			this.effectiveDate = effectiveDate;
			this.endDate = endDate;
			this.createdBy = createdBy;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public LocalDateTime getEffectiveDate() {
			return effectiveDate;
		}

		public void setEffectiveDate(LocalDateTime effectiveDate) {
			this.effectiveDate = effectiveDate;
		}

		public LocalDateTime getEndDate() {
			return endDate;
		}

		public void setEndDate(LocalDateTime endDate) {
			this.endDate = endDate;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}
	    
	    

}
