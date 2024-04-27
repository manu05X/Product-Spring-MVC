package com.example.productservicejanbatch.services;

import com.example.productservicejanbatch.models.Category;
import com.example.productservicejanbatch.models.Product;
import com.example.productservicejanbatch.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;
    public ProductServiceImpl(ProductRepo productRepo){
        this.productRepo = productRepo;
    }
    @Override
    public Product getProductById(Long id){
        //return null;
        //return "Product Fetch From Service. Id: " + id;

        /*//This can lead to null point exception in some case if product dont exist
            Product product = productRepo.findById(id);
            Category category = product.getCategory();
        */
        //Optional because it first checks if product is not null i.e it exist then only returns else null is returned
        //and our code does not goes into null pointer exception as at compile time we dont know if our product is null or not
        Optional<Product> product = this.productRepo.findById(id);
//        if(product.isPresent()){
//            Category category = product.get().getCategory();
//        }

        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public void deleteProducts() {

    }

    @Override
    public Product deleteProductById(Long id) {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public void updateProductById() {

    }
}
