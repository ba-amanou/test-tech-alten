package com.producttrialmaster.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.producttrialmaster.back.model.CartItem;


public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    List<CartItem> findByAccountEmail(String email);
    Optional<CartItem> findByAccountEmailAndProductId(String email, Long productId);    
}
