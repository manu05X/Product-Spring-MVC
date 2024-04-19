package com.example.productservicejanbatch.services;

import com.example.productservicejanbatch.exceptions.ProductNotFoundException;
import com.example.productservicejanbatch.models.Product;

import java.util.List;

public interface ProductService {
//    String getProductById(Long id);
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    void deleteProducts();
    void deleteProductById();
    void addProduct();
    void updateProductById();
}
