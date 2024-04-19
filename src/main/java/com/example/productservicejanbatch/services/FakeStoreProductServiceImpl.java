package com.example.productservicejanbatch.services;

import com.example.productservicejanbatch.dtos.FakeStoreProductDto;
import com.example.productservicejanbatch.models.Category;
import com.example.productservicejanbatch.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service("FakeProductService")
public class FakeStoreProductServiceImpl implements ProductService{
    //restTemplateBuilder is a webclient provided by spring this is use for http connection
    private RestTemplateBuilder restTemplateBuilder;

    private String getProductUrl = "https://fakestoreapi.com/products/1";//now we need clint to make a call at this url so we use restTemplateBuilder
    @Autowired
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

//    @Override
//    public String getProductById(Long id) {
//
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(getProductUrl, String.class);
//
//        //return "Product Fetch From Fake Service. Id: " + id;
//        return "Product Fetch From Fake Service. Id: " + responseEntity.toString();
//    }
//    @Override
//    public Product getProductById(Long id) {
//
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(getProductUrl, Product.class);
//
//        //return "Product Fetch From Fake Service. Id: " + id;
//        return  responseEntity.getBody();
//    }
    @Override
    public Product getProductById(Long id) {

    RestTemplate restTemplate = restTemplateBuilder.build();
    ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(getProductUrl, FakeStoreProductDto.class);

    //return "Product Fetch From Fake Service. Id: " + id;
    return  getProductFromFakeStoreProductDto(responseEntity.getBody());
}

    @Override
    public List<String> getAllProducts() {
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

    private Product getProductFromFakeStoreProductDto(FakeStoreProductDto fakeStoreProductDto){
        Product product= new Product();

        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDesc(fakeStoreProductDto.getDesc());

        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        product.setPrice(fakeStoreProductDto.getPrice());
        return product;

    }
}
