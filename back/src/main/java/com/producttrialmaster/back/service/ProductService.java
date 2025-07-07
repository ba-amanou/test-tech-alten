package com.producttrialmaster.back.service;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.producttrialmaster.back.dto.ProductRequestDTO;
import com.producttrialmaster.back.dto.ProductResponseDTO;
import com.producttrialmaster.back.exception.DuplicateProductCodeException;
import com.producttrialmaster.back.exception.ProductNotFoundException;
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

    public ProductResponseDTO saveProduct(ProductRequestDTO dto){
        Product product = ProductMapper.toEntity(dto);
        if (repository.existsByCode(dto.getCode())) {
            throw new DuplicateProductCodeException(dto.getCode());
        }
        Instant now = Instant.now();
        product.setCreatedAt(now);
        product.setUpdatedAt(now);
        Product newProduct = repository.save(product);
        return ProductMapper.toResponseDTO(newProduct);
    }

    public ProductResponseDTO getProductById(Long id){
        Product product =  repository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
        return ProductMapper.toResponseDTO(product);
    }

    public void deleteProduct(Long id){
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto){
        Product product = repository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
        if (dto.getName() != null) {
            product.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            product.setDescription(dto.getDescription());
        }
        if (dto.getImage() != null) {
            product.setImage(dto.getImage());
        }
        if (dto.getCategory() != null) {
            product.setCategory(dto.getCategory());
        }        
        if (dto.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }
        if (dto.getQuantity() != null) {
            product.setQuantity(dto.getQuantity());
        }
        if (dto.getShellId() != null) {
            product.setShellId(dto.getShellId());
        }
        if (dto.getInternalReference() != null) {
            product.setInternalReference(dto.getInternalReference());
        }
        if (dto.getRating() != null) {
            product.setRating(dto.getRating());
        }                                
        if (dto.getCode() != null && !dto.getCode().equals(product.getCode())) {
            if (repository.existsByCode(dto.getCode())) {
                throw new DuplicateProductCodeException(dto.getCode());
            }
            product.setCode(dto.getCode());
        }

        product.setUpdatedAt(Instant.now());

        Product updated = repository.save(product);
        return ProductMapper.toResponseDTO(updated);
    }
    
}
