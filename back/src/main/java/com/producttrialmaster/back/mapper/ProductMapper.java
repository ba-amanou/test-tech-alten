package com.producttrialmaster.back.mapper;

import com.producttrialmaster.back.dto.ProductImportDTO;
import com.producttrialmaster.back.dto.ProductRequestDTO;
import com.producttrialmaster.back.dto.ProductResponseDTO;
import com.producttrialmaster.back.model.Product;

public class ProductMapper {
    
    public static Product toEntity(ProductImportDTO dto){

    
    Product product = new Product();

    product.setCode(dto.getCode());
    product.setName(dto.getName());
    product.setDescription(dto.getDescription());
    product.setImage(dto.getImage());
    product.setCategory(dto.getCategory());
    product.setPrice(dto.getPrice());
    product.setQuantity(dto.getQuantity());
    product.setInternalReference(dto.getInternalReference());
    product.setShellId(dto.getShellId());
    product.setRating(dto.getRating());
    return product;    

    }

    public static Product toEntity(ProductRequestDTO dto){

    
    Product product = new Product();

    product.setCode(dto.getCode());
    product.setName(dto.getName());
    product.setDescription(dto.getDescription());
    product.setImage(dto.getImage());
    product.setCategory(dto.getCategory());
    product.setPrice(dto.getPrice());
    product.setQuantity(dto.getQuantity());
    product.setInternalReference(dto.getInternalReference());
    product.setShellId(dto.getShellId());
    product.setRating(dto.getRating());
    return product;    

    }    

    public static ProductResponseDTO toResponseDTO(Product product){
        return new ProductResponseDTO(
            product.getId(),
            product.getCode(),
            product.getName(),
            product.getDescription(),
            product.getImage(),
            product.getCategory(),
            product.getPrice(),
            product.getQuantity(),
            product.getInternalReference(),
            product.getShellId(),
            product.getInventoryStatus().name(),
            product.getRating(),
            product.getCreatedAt() != null ? product.getCreatedAt().toEpochMilli() : null,
            product.getUpdatedAt() != null ? product.getUpdatedAt().toEpochMilli() : null
        
        );

    }
}
