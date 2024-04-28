package com.example.productservicejanbatch.repos;

import com.example.productservicejanbatch.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    //As findByName doesNot exist so we need to create it for ProductServiceImpl in addProduct function for checking category
    Optional<Category> findByName(String name);
}
