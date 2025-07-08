package com.producttrialmaster.back.controller;

import com.producttrialmaster.back.dto.ProductRequestDTO;
import com.producttrialmaster.back.dto.ProductResponseDTO;
import com.producttrialmaster.back.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("@securityService.isAdmin(authentication.name)")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid@RequestBody ProductRequestDTO dto) {
        ProductResponseDTO productDTO = service.saveProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO productDTO = service.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }

    @PreAuthorize("@securityService.isAdmin(authentication.name)")
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO dto){
        ProductResponseDTO productDTO = service.updateProduct(id,dto);
        return ResponseEntity.ok(productDTO);
    }

    @PreAuthorize("@securityService.isAdmin(authentication.name)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
        return ResponseEntity.noContent().build(); // 204 no content
    }    
}
