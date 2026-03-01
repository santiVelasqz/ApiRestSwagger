package com.santiagomarin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.santiagomarin.exception.BusinessException;
import com.santiagomarin.dto.category.CategoryResponse;
import com.santiagomarin.dto.category.CreateCategoryRequest;
import com.santiagomarin.entities.Category;
import com.santiagomarin.entities.CategoryStatus;
import com.santiagomarin.mapper.CategoryMapper;
import com.santiagomarin.repository.CategoryRepository;
import com.santiagomarin.utils.StringUtils;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class); 
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryResponse createCategory(CreateCategoryRequest request) {
        String normalizedName = StringUtils.normalize(request.name());

        if (categoryRepository.existsByNameIgnoreCase(normalizedName)) {
            throw new BusinessException("Category already exists with name: " + request.name());
        }

        Category category = categoryMapper.toEntity(request);
        category.setName(normalizedName);
        category.setStatus(CategoryStatus.ACTIVE);
        categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
    	log.debug("Fetching all categories from database");
    	List<Category> categories = categoryRepository.findAll();
    	log.debug("Found {} categories in database", categories.size());
        return categoryMapper.toResponseList(categories);
    }

  
}
