package com.example.commerceDemo.domains.catalog.item.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Query("SELECT p FROM ItemEntity p ORDER BY p.itemId DESC")
    List<ItemEntity> findAllDesc();
    
}
