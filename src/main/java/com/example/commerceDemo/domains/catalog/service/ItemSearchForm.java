package com.example.commerceDemo.domains.catalog.service;

import com.example.commerceDemo.domains.catalog.presentation.Sorter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchForm {
    private String name;
    private Sorter sorter;
    private Long categoryId;
}
