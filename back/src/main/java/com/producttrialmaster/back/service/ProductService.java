package com.producttrialmaster.back.service;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.producttrialmaster.back.dto.ProductRequestDTO;
import com.producttrialmaster.back.dto.ProductResponseDTO;
import com.producttrialmaster.back.mapper.ProductMapper;
import com.producttrialmaster.back.model.Product;
import com.producttrialmaster.back.repository.ProductRepository;

@Service
public class ProductService {
    
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public List<ProductResponseDTO> getAllProducts(){
        return repository.findAll()
            .stream()
            .map(ProductMapper::toResponseDTO)
            .toList();
    }

    public Product saveProduct(ProductRequestDTO dto){
        Product product = ProductMapper.toEntity(dto);
        /* if (this.repository.existsByCode(dto.getCode())) {
            throw new DuplicateProductCodeException(dto.getCode());
        } */
        Instant now = Instant.now();
        product.setCreatedAt(now);
        product.setUpdatedAt(now);
        return repository.save(product);
    }
    
}
