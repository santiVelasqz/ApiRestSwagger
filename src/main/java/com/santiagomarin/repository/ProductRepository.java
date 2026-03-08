package com.santiagomarin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiagomarin.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Optional<Product> findBySku(String sku);
    boolean existsBySku(String sku);
    boolean existsBySkuIgnoreCase(String sku);
    boolean existsBySkuIgnoreCaseAndIdNot(String sku, Long id);
}
