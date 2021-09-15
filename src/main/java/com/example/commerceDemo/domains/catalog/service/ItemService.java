package com.example.commerceDemo.domains.catalog.service;

import com.example.commerceDemo.domains.catalog.domain.item.ItemEntity;
import com.example.commerceDemo.domains.catalog.domain.item.ItemRepository;
import com.example.commerceDemo.web.dto.item.ItemListResponseDto;
import com.example.commerceDemo.web.dto.item.ItemResponseDto;
import com.example.commerceDemo.web.dto.item.ItemSaveRequestDto;
import com.example.commerceDemo.web.dto.item.ItemUpdateStockQuantityRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long save(ItemSaveRequestDto requestDto) {
        return itemRepository.save(requestDto.toEntity()).getItemId();
    }

    @Transactional
    public Long addStockQuantity(Long id, ItemUpdateStockQuantityRequestDto requestDto) {
        ItemEntity itemEntity = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        itemEntity.addStockQuantity(requestDto.getStockQuantity());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        ItemEntity itemEntity = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        itemRepository.delete(itemEntity);
    }

    @Transactional(readOnly = true)
    public ItemResponseDto findById(Long id) {
        ItemEntity entity = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new ItemResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ItemListResponseDto> findAllDesc() {
        return itemRepository.findAllDesc().stream()
                .map(ItemListResponseDto::new)
                .collect(Collectors.toList());
    }
}
