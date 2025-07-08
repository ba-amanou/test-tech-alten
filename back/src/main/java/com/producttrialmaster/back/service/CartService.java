package com.producttrialmaster.back.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.producttrialmaster.back.dto.CartItemDTO;
import com.producttrialmaster.back.exception.ApiException;
import com.producttrialmaster.back.model.CartItem;
import com.producttrialmaster.back.repository.AccountRepository;
import com.producttrialmaster.back.repository.CartItemRepository;
import com.producttrialmaster.back.repository.ProductRepository;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;

    public CartService(CartItemRepository cartItemRepository, ProductRepository productRepository, AccountRepository accountRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.accountRepository = accountRepository;
    }

    public List<CartItem> getCartItems(String userEmail) {
        return cartItemRepository.findByAccountEmail(userEmail);
    }

    public CartItemDTO addItem(String userEmail, Long productId, int quantity) {
        //on verifie si l'item n'existe pas déjà dans le panier de l'utilisateur
        CartItem item = cartItemRepository.findByAccountEmailAndProductId(userEmail, productId)
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setAccount(accountRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("Utilisateur inexistant")));
                    newItem.setProduct(productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Produuit non trouvé")));
                    newItem.setQuantity(0);
                    return newItem;
                });
        item.setQuantity(item.getQuantity() + quantity);
        cartItemRepository.save(item);
        return CartItemDTO.fromEntity(item);
    }

    public CartItemDTO updateQuantity(String userEmail, Long productId, int quantity) {
        CartItem item = cartItemRepository.findByAccountEmailAndProductId(userEmail, productId)
                .orElseThrow(() -> new ApiException("Produit non trouvé", HttpStatus.NOT_FOUND));
        if (quantity <= 0) {
            cartItemRepository.delete(item);
        } else {
            item.setQuantity(quantity);
            cartItemRepository.save(item);
        }
        return CartItemDTO.fromEntity(item); 
    }

    public void removeItem(String userEmail, Long productId) {
        CartItem item = cartItemRepository.findByAccountEmailAndProductId(userEmail, productId)
                .orElseThrow(() -> new ApiException("Produit non trouvé", HttpStatus.NOT_FOUND));
        cartItemRepository.delete(item);
    }
}
