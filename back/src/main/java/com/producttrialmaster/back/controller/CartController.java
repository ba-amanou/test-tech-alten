package com.producttrialmaster.back.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producttrialmaster.back.dto.CartDTO;
import com.producttrialmaster.back.dto.CartItemAddDTO;
import com.producttrialmaster.back.dto.CartItemDTO;
import com.producttrialmaster.back.dto.CartItemUpdateQuantityDTO;
import com.producttrialmaster.back.model.CartItem;
import com.producttrialmaster.back.security.JwtTokenUtil;
import com.producttrialmaster.back.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final JwtTokenUtil jwtTokenUtil;

    public CartController(CartService cartService, JwtTokenUtil jwtTokenUtil) {
        this.cartService = cartService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping
    public CartDTO getCartItems(@RequestHeader("Authorization") String authHeader) {
        String email = extractEmailFromAuthHeader(authHeader);
        List<CartItem> items = cartService.getCartItems(email);
        List<CartItemDTO> itemsDTO = items.stream()
                    .map(CartItemDTO::fromEntity)
                    .collect(Collectors.toList());

        return new CartDTO(itemsDTO);
    }

    @PostMapping("/items")
    public ResponseEntity<CartItemDTO> addItem(
        @RequestHeader("Authorization") String authHeader,
        @RequestBody CartItemAddDTO dto
    ) {
        String email = extractEmailFromAuthHeader(authHeader);
        CartItemDTO itemDTO = cartService.addItem(email, dto.getId(), dto.getQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(itemDTO);
    }

    //pour le moment update juste la quantité
    @PatchMapping("/items/{productId}")
    public ResponseEntity<CartItemDTO> updateCartItem(
        @RequestHeader("Authorization") String authHeader,
        @PathVariable Long productId,
        @RequestBody CartItemUpdateQuantityDTO request
    ) {
        String email = extractEmailFromAuthHeader(authHeader);
        CartItemDTO updatedItemDTO = cartService.updateQuantity(email, productId, request.getQuantity());
        return ResponseEntity.ok(updatedItemDTO);
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeItem(
        @RequestHeader("Authorization") String authHeader,
        @PathVariable Long productId
    ) {
        String email = extractEmailFromAuthHeader(authHeader);
        cartService.removeItem(email, productId);
        return ResponseEntity.noContent().build();
    }

    private String extractEmailFromAuthHeader(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return jwtTokenUtil.getEmailFromToken(token);
    }
}
