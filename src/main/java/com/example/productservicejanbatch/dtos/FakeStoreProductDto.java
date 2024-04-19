package com.example.productservicejanbatch.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String desc;
    private Long price;
    private String category;
}
