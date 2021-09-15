package com.example.commerceDemo.web;

import com.example.commerceDemo.domains.catalog.domain.item.ItemEntity;
import com.example.commerceDemo.domains.catalog.domain.item.ItemRepository;
import com.example.commerceDemo.web.dto.item.ItemSaveRequestDto;
import com.example.commerceDemo.web.dto.item.ItemUpdateStockQuantityRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //랜덤포트 실행
public class ItemEntityApiControllerTest {

    @LocalServerPort
    private int port;
    //@WebMvc는 JPA테스트 지원 안함.
    //JPA등 외부 연동을 확인할때는 TestRestTemplate을 사용한다.
    //RestTestTemplate은 컨테이너를 직접 실행하여 테스팅
    @Autowired
    private TestRestTemplate restTemplate;


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    public void tearDown() {
        itemRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void item_등록된다() throws Exception {
        //given
        String name = "orange";
        String path = "imagePath";
        ItemSaveRequestDto requestDto = ItemSaveRequestDto.builder()
                .name(name)
                .imagePath(path)
                .price(1000)
                .stockQuantity(70000)
                .build();

        String url = "http://localhost:" + port + "/api/v1/items";

        //when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());
        //then

        List<ItemEntity> all = itemRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getImagePath()).isEqualTo(path);
        assertThat(all.get(0).getPrice()).isEqualTo(1000);

    }

    @Test
    @WithMockUser(roles = "USER")
    public void item_재고량_추가() throws Exception {
        //given
        int STOCK_QUANTITY = 1500000;
        ItemEntity savedItemEntity = itemRepository.save(ItemEntity.builder().name("apple").stockQuantity(STOCK_QUANTITY).build());
        Long updateId = savedItemEntity.getItemId();

        int ADD_STOCK_QUANTITY = 1500;

        ItemUpdateStockQuantityRequestDto requestDto = ItemUpdateStockQuantityRequestDto.builder().stockQuantity(ADD_STOCK_QUANTITY).build();

        String url = "http://localhost:" + port + "/api/v1/items/" + updateId + "/stock-quantity/add";

        HttpEntity<ItemUpdateStockQuantityRequestDto> requestEntity = new HttpEntity<>((requestDto));

        //when
        mvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());
        //then
        List<ItemEntity> all = itemRepository.findAll();
        assertThat(all.get(0).getStockQuantity()).isEqualTo(STOCK_QUANTITY + ADD_STOCK_QUANTITY);

    }


}