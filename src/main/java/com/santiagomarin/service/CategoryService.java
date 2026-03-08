package com.santiagomarin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.santiagomarin.exception.BusinessException;
import com.santiagomarin.exception.ResourceNotFoundException;
import com.santiagomarin.dto.category.CategoryResponse;
import com.santiagomarin.dto.category.CreateCategoryRequest;
import com.santiagomarin.dto.category.UpdateCategoryRequest;
import com.santiagomarin.entities.Category;
import com.santiagomarin.entities.CategoryStatus;
import com.santiagomarin.mapper.CategoryMapper;
import com.santiagomarin.repository.CategoryRepository;
import com.santiagomarin.utils.StringUtils;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) 
public class CategoryService {

	private static final Logger log = LoggerFactory.getLogger(CategoryService.class); 
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Transactional
    public CategoryResponse createCategory(CreateCategoryRequest request) {
    	
    	log.debug("Creating new Category ");
        String normalizedName = StringUtils.normalize(request.name());

        if (categoryRepository.existsByNameIgnoreCase(normalizedName)) {
            throw new BusinessException("Category already exists with name: " + request.name());
        }

        Category category = categoryMapper.toEntity(request);
        category.setName(request.name().trim());
        category.setNameNormalized(normalizedName);
        category.setStatus(CategoryStatus.ACTIVE);
        categoryRepository.save(category);
        log.info("Category created successfully with ID: {}",  category.getId());
        return categoryMapper.toResponse(category);
    }

    public List<CategoryResponse> findAll() {
    	log.debug("Fetching all categories from database");
        return categoryMapper.toResponseList(categoryRepository.findAll());
    }
    
    public CategoryResponse findById(Long id) {
    	log.debug("Fetching category from database");
    	Category category = categoryRepository.findById(id)
    			.orElseThrow(()->  new ResourceNotFoundException("Category", "id",id));
    	log.info("Category found: {}", category.getId());
    	return categoryMapper.toResponse(category);
    }
    
    @Transactional
    public CategoryResponse updateCategory(Long id, UpdateCategoryRequest request) {
    	
    	log.debug("Updating category with ID: {}", id);
    	Category category = categoryRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Category","id",id));
    	
    	if(request.name() != null) {
    		String nameNormalized = StringUtils.normalize(request.name());
	    	if(!nameNormalized.equals(category.getNameNormalized()) && categoryRepository.existsByNameNormalized(nameNormalized)) {
	    		throw new BusinessException("Category already exists with this name");
	    	}
	    	category.setNameNormalized(nameNormalized);
    	}
    	
    	categoryMapper.updateEntity(category, request);
    	categoryRepository.save(category);
    	log.info("Category updated successfully with id: {}", category.getId());
    	return categoryMapper.toResponse(category);
    }
    
    @Transactional
    public void delete(Long id) {
    	
    	log.debug("Deleting category with ID: {}", id);
    	Category category = categoryRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Category","id",id));
    	categoryRepository.delete(category);
    	log.info("Category deleted successfully with ID: {}", id);
    }

  
}
