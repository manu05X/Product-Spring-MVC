package com.example.productservicejanbatch.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product extends BaseModel {
    private String title;
    private String desc;
    private Long price;
    private Category category;
}
