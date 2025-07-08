package com.producttrialmaster.back.model;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
@Table(name="products", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class Product {

    //quantité a partir de laquelle le status stock deviendra en lowstock ( stock faible )
    //possibilité d'externaliser dans le properties
    public static final int LOW_STOCK_THRESHOLD = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String image;

    private String category;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    private String internalReference;

    private Long shellId;

    private Integer rating;

    private Instant createdAt;

    private Instant updatedAt;

    public Product() {}

    //enum pour le statut inventaire dérivé de la quantité produit
    public enum InventoryStatus {
        INSTOCK, LOWSTOCK, OUTOFSTOCK;
        
        public static InventoryStatus fromQuantity(int quantity) {
            if (quantity == 0) return OUTOFSTOCK;
            if (quantity < LOW_STOCK_THRESHOLD) return LOWSTOCK;
            return INSTOCK;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Transient
    public InventoryStatus getInventoryStatus() {
        return InventoryStatus.fromQuantity(this.quantity != null ? this.quantity : 0);
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    

}
