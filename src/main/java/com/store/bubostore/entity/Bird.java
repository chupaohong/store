package com.store.bubostore.entity;

import javax.persistence.*;

@Entity
public class Bird {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int quantity;
    private String description;
    private Float originalPrice;
    private Float price;
    private Boolean specialItem;

    // Constructor
    public Bird() {
    }

    public Bird(String name, int quantity, String description, Float originalPrice, Float price, Boolean specialItem) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.specialItem = specialItem;
        if (originalPrice != 0) {
            this.originalPrice = originalPrice;
        }
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOriginalPrice(Float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setSpecialItem(Boolean specialItem) {
        this.specialItem = specialItem;
    }

    // Getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public Float getOriginalPrice() {
        return originalPrice;
    }

    public Float getPrice() {
        return price;
    }

    public Boolean getSpecialItem() {
        return specialItem;
    }
}