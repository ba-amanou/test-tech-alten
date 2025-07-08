package com.producttrialmaster.back.repository;

import com.producttrialmaster.back.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  boolean existsByCode(String code);
}
