package com.example.productservicejanbatch.services;

import com.example.productservicejanbatch.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("SelfProductService")
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long id){
        return null;
        //return "Product Fetch From Service. Id: " + id;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public void deleteProducts() {

    }

    @Override
    public void deleteProductById() {

    }

    @Override
    public void addProduct() {

    }

    @Override
    public void updateProductById() {

    }
}
