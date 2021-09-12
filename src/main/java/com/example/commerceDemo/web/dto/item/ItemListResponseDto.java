package com.example.commerceDemo.web.dto.item;

import com.example.commerceDemo.domains.catalog.item.domain.ItemEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemListResponseDto {
    private Long itemId;
    private String name;
    private String imagePath;
    private LocalDateTime modifiedDate;

    public ItemListResponseDto(ItemEntity entity) {
        this.itemId = entity.getItemId();
        this.name = entity.getName();
        this.imagePath = entity.getImagePath();
        this.modifiedDate = entity.getModifiedDate();
    }
}
