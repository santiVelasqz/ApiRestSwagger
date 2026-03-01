package com.santiagomarin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiagomarin.entities.ProductPrice;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {
	
	Optional<ProductPrice> findByProductIdAndEndDateIsNull(Long productId);
	
	

}
