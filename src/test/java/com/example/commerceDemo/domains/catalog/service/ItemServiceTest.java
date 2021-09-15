package com.example.commerceDemo.domains.catalog.service;

import com.example.commerceDemo.domains.catalog.domain.item.ItemEntity;
import com.example.commerceDemo.domains.catalog.domain.item.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void 아이템_추가() {
        //given
        ItemEntity entity = ItemEntity.builder().name("치킨너겟").build();
        given(itemRepository.save(any(ItemEntity.class))).willReturn(entity);
        //when
        ItemEntity result = itemRepository.save(entity);

        //then
        Assertions.assertThat(result.getName()).isEqualTo(entity.getName());
    }


}