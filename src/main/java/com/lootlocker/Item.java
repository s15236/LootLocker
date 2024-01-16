package com.lootlocker;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.UUID;

public class Item {
    private String id;
    @NotBlank(message = "Please choose a type")
    private String type;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Rarity cannot be blank")
    private String rarity;
    @Min(value = 0, message = "Price cannot be negative")
    @NotNull(message = "Price cannot be blank")
    private Double price;
    @Past(message = "Date must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Item() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
