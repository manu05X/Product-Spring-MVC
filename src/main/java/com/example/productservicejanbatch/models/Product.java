package com.example.productservicejanbatch.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Table(name = "product")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
