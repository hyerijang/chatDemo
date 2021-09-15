package com.example.commerceDemo.web;

import com.example.commerceDemo.domains.catalog.service.ItemService;
import com.example.commerceDemo.web.dto.item.ItemResponseDto;
import com.example.commerceDemo.web.dto.item.ItemSaveRequestDto;
import com.example.commerceDemo.web.dto.item.ItemUpdateStockQuantityRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController //RestController!
public class ItemApiController {

    private final ItemService itemService;

    @PostMapping("/api/v1/items")
    public Long save(@RequestBody ItemSaveRequestDto requestDto) {
        return itemService.save(requestDto);
    }


    @GetMapping("/api/v1/items/{id}")
    public ItemResponseDto findByID(@PathVariable Long id) {
        return itemService.findById(id);
    }

    @DeleteMapping("/api/v1/items/{id}")
    public Long delete(@PathVariable Long id) {
        itemService.delete(id);
        return id;
    }

    @PutMapping("/api/v1/items/{id}/stock-quantity/add")
    public Long addStockQuantity(@PathVariable Long id, @RequestBody ItemUpdateStockQuantityRequestDto requestDto) {
        return itemService.addStockQuantity(id, requestDto);
    }


}
