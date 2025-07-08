package com.producttrialmaster.back.dto;

import com.producttrialmaster.back.model.CartItem;

public class CartItemDTO {
    private ProductResponseDTO product;
    private int quantity;

public static CartItemDTO fromEntity(CartItem cartItem) {
    CartItemDTO dto = new CartItemDTO();
    dto.setProduct(ProductResponseDTO.fromEntity(cartItem.getProduct()));
    dto.setQuantity(cartItem.getQuantity());
    return dto;
}

    
    public ProductResponseDTO getProduct() {
        return product;
    }
    public void setProduct(ProductResponseDTO product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    } 

    
}
