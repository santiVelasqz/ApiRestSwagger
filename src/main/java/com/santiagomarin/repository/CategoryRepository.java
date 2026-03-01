package com.santiagomarin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiagomarin.entities.Category;
import com.santiagomarin.entities.CategoryStatus;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	Optional<Category> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
    List<Category> findByStatus(CategoryStatus status);
}
