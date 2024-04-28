package com.example.productservicejanbatch.repos;

import com.example.productservicejanbatch.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//import java.util.UUID;

public interface ProductRepo extends JpaRepository <Product, Long>{
    //Optional because in some case id can be null
    Optional<Product> findById(Long id);
    //even if we comment the above line we will  ot get error because Jpa Repo take care of that

}
