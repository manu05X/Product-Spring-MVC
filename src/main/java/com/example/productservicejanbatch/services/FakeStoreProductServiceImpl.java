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

import java.util.LinkedList;
import java.util.List;
@Service("FakeProductService")
public class FakeStoreProductServiceImpl implements ProductService{
    //restTemplateBuilder is a webclient provided by spring this is use for http connection
    private RestTemplateBuilder restTemplateBuilder;

    private String getProductUrl = "https://fakestoreapi.com/products/1";//now we need clint to make a call at this url so we use restTemplateBuilder
    private String genericProductUrl = "https://fakestoreapi.com/products";
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
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(genericProductUrl, FakeStoreProductDto[].class);
        
        List<Product> productList = new LinkedList<>();
        for(FakeStoreProductDto fakeStoreProductDto: responseEntity.getBody()){
            productList.add(getProductFromFakeStoreProductDto(fakeStoreProductDto));
        }

        return productList;
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

        //Mapper to create the object according to 3rd party API i.e fakeStoreAPI
        //For us category is objects and for fake store it is string, so we have converted to object
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        product.setPrice(fakeStoreProductDto.getPrice());
        return product;

    }
}


/**
 * Map<String,Object>
 * Map<id,1>
 *
 *     ObjectMapper - Map the JSON Map to an Object if an error it throws that error
 *     Most common library is jacson
 *
 *     Here we are using the same i.e getForEntity is used for mapping response from url to obj of Dto
 *      ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(getProductUrl, FakeStoreProductDto.class);
* */