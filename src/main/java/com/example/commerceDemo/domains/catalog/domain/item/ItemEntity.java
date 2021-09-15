package com.example.commerceDemo.domains.catalog.domain.item;

import com.example.commerceDemo.common.config.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "items")
public class ItemEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    @Column(length = 500, nullable = false)
    private String name;
    private String imagePath;
    private int price;
    private int stockQuantity;
    private Long categoryId;

    @Builder
    public ItemEntity(Long itemId, String name, String imagePath, int price, int stockQuantity, Long categoryId) {
        this.itemId = itemId;
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
    }

    // ==== 비즈니스 로직 ====
    public void addStockQuantity(int stockQuantity) {
        this.stockQuantity += stockQuantity;
    }
}
