package com.santiagomarin.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santiagomarin.dto.productprice.CreateProductPriceRequest;
import com.santiagomarin.dto.productprice.ProductPriceResponse;
import com.santiagomarin.entities.Product;
import com.santiagomarin.entities.ProductPrice;
import com.santiagomarin.exception.ResourceNotFoundException;
import com.santiagomarin.mapper.ProductPriceMapper;
import com.santiagomarin.repository.ProductPriceRepository;
import com.santiagomarin.repository.ProductRepository;

@Service
@Transactional(readOnly = true)
public class ProductPriceService {

	private static final Logger log = LoggerFactory.getLogger(ProductPriceService.class);
	private ProductPriceRepository productPriceRepository;
	private ProductRepository productRepository;
	private ProductPriceMapper productPriceMapper;
	
	public ProductPriceService(ProductPriceRepository productPriceRepository,
			ProductRepository productRepository, ProductPriceMapper productPriceMapper
			) {
		this.productPriceRepository = productPriceRepository;
		this.productRepository = productRepository;
		this.productPriceMapper = productPriceMapper;
	}
	
	@Transactional
	public ProductPriceResponse addPrice(Long productId, CreateProductPriceRequest request) {
		
		log.debug("Adding new price to product ID: {}", productId);
		Product product = productRepository.findById(productId)
				.orElseThrow(()-> new ResourceNotFoundException("Product","id",productId));
		// put endDate
		productPriceRepository.findByProductIdAndEndDateIsNull(productId)
		.ifPresent(current -> {
			current.setEndDate(LocalDateTime.now());
			productPriceRepository.save(current);
			log.debug("Closed previous price ID: {}", current.getId());
		});
		//add new price and present
		ProductPrice newPrice  = productPriceMapper.toEntity(request);
		newPrice.setProduct(product);
		newPrice.setEffectiveDate(LocalDateTime.now());
		
		ProductPrice saved = productPriceRepository.save(newPrice);
		log.info("New price added to product ID: {} → {}", productId, saved.getPrice());
		return productPriceMapper.toResponse(saved);
	}
	
	public List<ProductPriceResponse> getPriceHistory(Long productId) {
		
		log.debug("Fetching price history for product ID: {}", productId);
		
		productRepository.findById(productId)
		.orElseThrow(() -> new ResourceNotFoundException("Product","id",productId));
		return productPriceMapper.toResponseList(productPriceRepository.findByProductIdOrderByEffectiveDateDesc(productId));
	}
	
	
   
}
