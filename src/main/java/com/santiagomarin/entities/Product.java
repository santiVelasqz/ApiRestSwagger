package com.santiagomarin.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="product")

public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status; // ACTIVE, DISCONTINUED, OUT_OF_STOCK

    @Column(name = "sku", unique = true, nullable = false)
    private String sku; // Código único del producto
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity = 0;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("effectiveDate DESC")
    private List<ProductPrice> priceHistory = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    private void normalizeSku() {
        if (this.sku != null) {
            this.sku = this.sku.trim().toUpperCase();
        }
    }
	
	public Product () {
		
	}
	
	 public Product(Long id, String name, String description, ProductStatus status, String sku, Category category, Integer stockQuantity,
			List<ProductPrice> priceHistory, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.sku = sku;
		this.stockQuantity = stockQuantity;
		this.priceHistory = priceHistory;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	 
	 
	 
	 // Método utilitario para obtener el precio vigente
    public BigDecimal getCurrentPrice() {
        return priceHistory.stream()
                .filter(p -> p.getEndDate() == null)
                .findFirst()
                .map(ProductPrice::getPrice)
                .orElse(null);
    }
	
	public Long getId() {
		return id;
	}

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public String getName() {
		 return name;
	 }

	 public void setName(String name) {
		 this.name = name;
	 }

	 public String getDescription() {
		 return description;
	 }

	 public void setDescription(String description) {
		 this.description = description;
	 }

	 public ProductStatus getStatus() {
		 return status;
	 }

	 public void setStatus(ProductStatus status) {
		 this.status = status;
	 }

	 public String getSku() {
		 return sku;
	 }

	 public void setSku(String sku) {
		 this.sku = sku;
	 }

	 public Category getCategory() {
		return category;
	}

	 public void setCategory(Category category) {
		 this.category = category;
	 }

	 public Integer getStockQuantity() {
		 return stockQuantity;
	 }

	 public void setStockQuantity(Integer stockQuantity) {
		 this.stockQuantity = stockQuantity;
	 }

	 public List<ProductPrice> getPriceHistory() {
		 return priceHistory;
	 }

	 public void setPriceHistory(List<ProductPrice> priceHistory) {
		 this.priceHistory = priceHistory;
	 }

	 public LocalDateTime getCreatedAt() {
		 return createdAt;
	 }

	 public void setCreatedAt(LocalDateTime createdAt) {
		 this.createdAt = createdAt;
	 }

	 public LocalDateTime getUpdatedAt() {
		 return updatedAt;
	 }

	 public void setUpdatedAt(LocalDateTime updatedAt) {
		 this.updatedAt = updatedAt;
	 }

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id != null && id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status + ", sku="
				+ sku + ", stockQuantity=" + stockQuantity + ", priceHistory=" + priceHistory + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
    
    
    
    
}
