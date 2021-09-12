package com.example.commerceDemo.web.dto.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemUpdateStockQuantityRequestDto {
    private int stockQuantity;

    @Builder
    public ItemUpdateStockQuantityRequestDto(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
