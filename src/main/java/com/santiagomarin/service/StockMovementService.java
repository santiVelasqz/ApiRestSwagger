package com.santiagomarin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santiagomarin.dto.stockmovement.AdjustStockRequest;
import com.santiagomarin.dto.stockmovement.StockMovementResponse;
import com.santiagomarin.entities.Product;
import com.santiagomarin.entities.StockMovement;
import com.santiagomarin.entities.StockMovementReason;
import com.santiagomarin.exception.BusinessException;
import com.santiagomarin.exception.ResourceNotFoundException;
import com.santiagomarin.mapper.StockMovementMapper;
import com.santiagomarin.repository.ProductRepository;
import com.santiagomarin.repository.StockMovementRepository;

@Service
@Transactional(readOnly=true)
public class StockMovementService {
	
	private static final Logger log = LoggerFactory.getLogger(StockMovementService.class); 
	private final StockMovementRepository stockMovementRepository;
	private final ProductRepository productRepository;
	private final StockMovementMapper stockMovementMapper;
	
	public StockMovementService(StockMovementRepository stockMovementRepository, ProductRepository productRepository, StockMovementMapper stockMovementMapper) {
		this.stockMovementRepository = stockMovementRepository;
		this.productRepository = productRepository;
		this.stockMovementMapper = stockMovementMapper;
	}
	
	@Transactional
	public StockMovementResponse adjustStock(Long productId, AdjustStockRequest request) {
		
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product","id", productId));
		
		validateReasonAndQuantity(request.reason(), request.quantity());
		
		int newStock = product.getStockQuantity() + request.quantity();
		if(newStock < 0) {
			throw new BusinessException(
					"Insufficient stock. Current: " + product.getStockQuantity() +
					", requested: " + request.quantity()
					);
		}	
		// update product stock
        product.setStockQuantity(newStock);
        productRepository.save(product);
        
        StockMovement movement = stockMovementMapper.toEntity(request);
        movement.setProduct(product);
        movement.setCreatedBy("aqui tengo que relacionarlo con la session del usuario.");
        
        StockMovement saved = stockMovementRepository.save(movement);
        log.info("Stock adjusted for product ID: {} → new stock: {}", productId, newStock);
		
		return stockMovementMapper.toResponse(saved);
	}
	
	public List<StockMovementResponse> getMovementsByProduct(Long productId) {
        log.debug("Fetching stock movements for product ID: {}", productId);

        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product", "id", productId);
        }

        return stockMovementMapper.toResponseList(
                stockMovementRepository.findByProductIdOrderByCreatedAtDesc(productId)
        );
    }
	
	private void validateReasonAndQuantity(StockMovementReason reason, Integer quantity) {
        switch (reason) {
            case PURCHASE, RETURN -> {
                if (quantity <= 0)
                    throw new BusinessException(reason + " must have positive quantity");
            }
            case SALE, DAMAGE -> {
                if (quantity >= 0)
                    throw new BusinessException(reason + " must have negative quantity");
            }
            case ADJUSTMENT -> {
                if (quantity == 0)
                    throw new BusinessException("ADJUSTMENT quantity cannot be zero");
            }
        }
    }
	


   
}
