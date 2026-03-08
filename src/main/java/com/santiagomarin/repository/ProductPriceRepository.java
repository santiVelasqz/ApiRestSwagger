package com.santiagomarin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiagomarin.entities.ProductPrice;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {
	
	Optional<ProductPrice> findByProductIdAndEndDateIsNull(Long productId);
	
	List<ProductPrice> findByProductIdOrderByEffectiveDateDesc(Long productId);
	
}
