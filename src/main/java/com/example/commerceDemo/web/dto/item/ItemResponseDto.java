package com.example.commerceDemo.web.dto.item;

import com.example.commerceDemo.domains.catalog.domain.item.ItemEntity;
import lombok.Getter;

@Getter
public class ItemResponseDto {

    private Long id;
    private String imagePath;
    private String name;
    private int price;
    private int stockQuantity;


    public ItemResponseDto(ItemEntity entity) {
        this.id = entity.getItemId();
        this.imagePath = entity.getImagePath();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.stockQuantity = entity.getStockQuantity();
    }
}
