package com.example.productservicejanbatch.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private Long price;
    @ManyToOne
    private Category category;

    //private List<String> allowedUser;
}
/**
 * 1 product - 1 catagory
 * 1 category - M product
 */
