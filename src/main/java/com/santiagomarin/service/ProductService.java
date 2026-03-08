package com.santiagomarin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santiagomarin.dto.product.CreateProductRequest;
import com.santiagomarin.dto.product.ProductResponse;
import com.santiagomarin.dto.product.UpdateProductRequest;
import com.santiagomarin.entities.Category;
import com.santiagomarin.entities.Product;
import com.santiagomarin.entities.ProductStatus;
import com.santiagomarin.exception.BusinessException;
import com.santiagomarin.exception.ResourceNotFoundException;
import com.santiagomarin.mapper.ProductMapper;
import com.santiagomarin.repository.CategoryRepository;
import com.santiagomarin.repository.ProductRepository;

@Service
@Transactional(readOnly = true)
public class ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductService.class); 
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	private ProductMapper productMapper;
	
	public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository, ProductMapper productMapper) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.productMapper = productMapper;
	}
	
	public List<ProductResponse> findAll(){
		log.debug("Fetching all products from database");
		return productMapper.toResponseList(productRepository.findAll());
	}
	
	public ProductResponse findById(Long id) {
		log.debug("Fetching product from database");
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product","id",id));
		log.info("Product found: {}", product.getId());
		return productMapper.toResponse(product);
	}
	
	@Transactional
	public ProductResponse create(CreateProductRequest request) {
		
		log.debug("Creating new Product ");
		String normalizedSku = request.sku().trim().toUpperCase();
		if(productRepository.existsBySku(normalizedSku)) {
			throw new BusinessException("Product already exists with SKU: " + request.sku());
		}
		Product product = productMapper.toEntity(request);
		product.setStatus(ProductStatus.ACTIVE);
		product.setStockQuantity(0);
		
		if(request.categoryId() != null) {
			Category category = categoryRepository.findById(request.categoryId())
					.orElseThrow(()-> new ResourceNotFoundException("Category","id",request.categoryId()));
			product.setCategory(category);
		}
		
		Product savedProduct = productRepository.save(product);	
		log.info("Product created successfully with ID: {}", savedProduct.getId());
		return productMapper.toResponse(savedProduct);
	}
	
	@Transactional
	public ProductResponse update(Long id, UpdateProductRequest request) {
		log.debug("Updating product with ID: {}", id);
		Product product = productRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Product","id",id));
		
		if(request.sku() != null) {
			String newSku = request.sku().trim().toUpperCase();
			if(!newSku.equalsIgnoreCase(product.getSku()) && productRepository.existsBySkuIgnoreCaseAndIdNot(newSku, id)) {
				throw new BusinessException("Product already exists with this sku");
			}
		}
		
		if (request.categoryId() != null) {
	        Category category = categoryRepository.findById(request.categoryId())
	                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", request.categoryId()));
	        product.setCategory(category);
	    }
		
		productMapper.updateEntity(product, request);
		Product savedProduct = productRepository.save(product);
		log.info("Product updated successfully with ID: {}", savedProduct.getId());
		return productMapper.toResponse(savedProduct);
	}
	
	@Transactional
	public void delete(Long id) {
		log.debug("Deleting product with ID: {}", id);
		Product product = productRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Product","id",id));
		productRepository.delete(product);
		log.info("Product deleted successfully with ID: {}", id);
	}

}
