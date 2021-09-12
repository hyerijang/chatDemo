package com.example.commerceDemo.web.dto.item;

import com.example.commerceDemo.domains.catalog.item.domain.ItemEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemSaveRequestDto {
    private Long itemId;
    private String imagePath;
    private String name;
    private int price;
    private int stockQuantity;
    private Long categoryId;

    @Builder
    public ItemSaveRequestDto(String imagePath, String name, int price, int stockQuantity, Long categoryId) {
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
    }


    public ItemEntity toEntity() {
        return ItemEntity.builder()
                .name(name)
                .price(price)
                .imagePath(imagePath)
                .stockQuantity(stockQuantity)
                .categoryId(categoryId)
                .build();
    }

}
