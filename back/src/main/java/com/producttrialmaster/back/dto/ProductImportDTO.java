package com.producttrialmaster.back.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductImportDTO {
    @NotBlank
    public String code;
    @NotBlank
    public String name;
    public String description;
    public String image;
    public String category;
    @PositiveOrZero
    public Double price;
    @PositiveOrZero
    public Integer quantity;
    public String internalReference;
    public Long shellId;
    @PositiveOrZero
    public Integer rating;    
    private Long createdAt;
    private Long updatedAt;
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getInternalReference() {
        return internalReference;
    }
    public void setInternalReference(String internalReference) {
        this.internalReference = internalReference;
    }
    public Long getShellId() {
        return shellId;
    }
    public void setShellId(Long shellId) {
        this.shellId = shellId;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
    public Long getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }    

    
}
