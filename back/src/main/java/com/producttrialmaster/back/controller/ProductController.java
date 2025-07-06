package com.producttrialmaster.back.controller;

import com.producttrialmaster.back.dto.ProductRequestDTO;
import com.producttrialmaster.back.dto.ProductResponseDTO;
import com.producttrialmaster.back.mapper.ProductMapper;
import com.producttrialmaster.back.model.Product;
import com.producttrialmaster.back.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return service.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO dto) {
        Product saved = service.saveProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toResponseDTO(saved));
    }
}
