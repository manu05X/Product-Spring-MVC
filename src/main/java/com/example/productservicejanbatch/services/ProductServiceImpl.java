package com.example.productservicejanbatch.services;

import com.example.productservicejanbatch.exceptions.ProductNotFoundException;
import com.example.productservicejanbatch.models.Category;
import com.example.productservicejanbatch.models.Product;
import com.example.productservicejanbatch.repos.CategoryRepo;
import com.example.productservicejanbatch.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

//    public ProductServiceImpl(ProductRepo productRepo){
//        this.productRepo = productRepo;
//    }
    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo){
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
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

    /**
     * We are given a product by our client, we need to save it in our system(DB)
     *
     * a> Client doesnot give us id of product category as its our internal so they dont know
     * b> So first get the product category Name from the details provided by client.
     * c> Check if is present in our system
     * d> if Present then add the product in that Category
     *   else
     *      -Create new Category as per Product Detail
     *      -Then add the product in that Category
     *  e> Now save the Product and return
     *
     */
    @Override
    public Product addProduct(Product product) {
        //return null;
        //return this.productRepo.save(product);
        //As product is dependent on Category so we must first check if it exists or not

        Optional<Category> categoryOptional = this.categoryRepo.findByName(product.getCategory().getName());
        if(categoryOptional.isPresent()){
            product.setCategory(categoryOptional.get());
        }else {
            //if not present in list previously then create it. It migth be the case that its first of its kind in list
            Category category = categoryRepo.save(product.getCategory());
            product.setCategory(category);
        }
        //saving
       return this.productRepo.save(product);
    }

    /*
    * C - for Create -> we will use save
    * U - for update ?
    * R - for read -> find by, get by
    * D - for delete -> delete By
    *
    * The save will internally take care of update
    * */

    @Override
    @Transactional
    public void updateProductById(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> existingProductOptional = this.productRepo.findById(id);
        if(existingProductOptional.isPresent()){
            Product existingProduct = new Product();

            existingProduct.setTitle(product.getTitle());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());

            Optional<Category> categoryOptional = this.categoryRepo.findByName(product.getCategory().getName());
            if(categoryOptional.isPresent()){
                existingProduct.setCategory(categoryOptional.get());
            }else {
                //if not present in list previously then create it. It migth be the case that its first of its kind in list
                Category category = categoryRepo.save(product.getCategory());
                product.setCategory(category);
            }
            productRepo.save(existingProduct);
        } else {
            throw new ProductNotFoundException("Product not found");
        }
    }
}

