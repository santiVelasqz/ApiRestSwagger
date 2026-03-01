package com.santiagomarin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiagomarin.entities.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement,Long>{

	List<StockMovement> findByProductIdOrderByCreatedAtDesc(Long productId);
}
