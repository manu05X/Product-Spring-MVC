package com.example.productservicejanbatch.services;

import com.example.productservicejanbatch.dtos.FakeStoreProductDto;
import com.example.productservicejanbatch.exceptions.ProductNotFoundException;
import com.example.productservicejanbatch.models.Category;
import com.example.productservicejanbatch.models.Product;
import com.example.productservicejanbatch.thirdpartyclients.FakeStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
@Service("FakeProductService")
public class FakeStoreProductServiceImpl implements ProductService{

    private FakeStoreClient fakeStoreClient;
    @Autowired
    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient){
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {

        return  getProductFromFakeStoreProductDto(fakeStoreClient.getProductById(id));
}

    @Override
    public List<Product> getAllProducts() {

        List<Product> productList = new LinkedList<>();
        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreClient.getAllProducts()){
            productList.add(getProductFromFakeStoreProductDto(fakeStoreProductDto));
        }

        return productList;
    }

    @Override
    public void deleteProducts() {

    }

    @Override
    public Product deleteProductById(Long id) {
       return getProductFromFakeStoreProductDto(fakeStoreClient.deleteProductById(id));
    }


    @Override
    public Product addProduct(Product product) {

        return getProductFromFakeStoreProductDto(fakeStoreClient.addProduct(getFakeStoreProductDtoFromProduct(product)));
    }

    @Override
    public void updateProductById(Long id, Product product) throws ProductNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Product getProductFromFakeStoreProductDto(FakeStoreProductDto fakeStoreProductDto){
        Product product= new Product();
        //commented because from fakestore we got as Long but we change it from long to uuid
        //product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDesc());

        //Mapper to create the object according to 3rd party API i.e fakeStoreAPI
        //For us category is objects and for fake store it is string, so we have converted to object
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        product.setPrice(fakeStoreProductDto.getPrice());
        return product;

    }

    //Reverse mapper for adding product
    private FakeStoreProductDto getFakeStoreProductDtoFromProduct(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDesc(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setPrice(product.getPrice());

        return fakeStoreProductDto;

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

/**
 * Who is throwing exception? ans: service
 * Who should handle it? Contoller
 * Similar to MVC i.e if cheff is saying that i cannot prepare a specific food item.
 * who should handle it ? ans: Waiter i.e controller
 * So write exceptional handler in controller
 *
* */


/**
 * FakeStoreProductServiceImpl is doing lot of work such as:
 * a> Handle the 3rd party call
 * b> Mapping
 *
 * Now which is business logic?
 * Mapping is business logic for service module. So put 3rd party handling into another
 *
 */

/**
 * So we Refactor our code to make a> Mapping i.e our business logic for Service module and b> Handle the 3rd party call
 * There for we make another module called as thirdpartyclient for Handle the 3rd party call.
 * This will make our code clean in service file.
 * Now if tomarrow say our client i.e url(fakestore api) changes the format our service will be least impacted and we only need to change the
 * Mapping logic i.e getFakeStoreProductDtoFromProduct and getProductFromFakeStoreProductDto rest every this will be
 * as it is.
 *
 *This is a Variation of Adapter Design Pattern so we separate both:
 *  * a> Handle the 3rd party call
 *  * b> Mapping
 */
