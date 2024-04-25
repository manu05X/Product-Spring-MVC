package com.example.productservicejanbatch.thirdpartyclients;

import com.example.productservicejanbatch.dtos.FakeStoreProductDto;
import com.example.productservicejanbatch.exceptions.ProductNotFoundException;
import com.example.productservicejanbatch.models.Category;
import com.example.productservicejanbatch.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

//Creating bean of FakeStoreClient using component as we are not sure what it is i.e service or controller then use component
@Component
public class FakeStoreClient {
    //restTemplateBuilder is a webclient provided by spring this is use for http connection
    private RestTemplateBuilder restTemplateBuilder;

    //private String getProductUrl = "https://fakestoreapi.com/products/1";//now we need clint to make a call at this url so we use restTemplateBuilder
   // private String specificProductUrl = "https://fakestoreapi.com/products/{id}";
    private String specificProductUrl;
    //below is sinilar to field Injection , but this is not recommended above one is better way to use
    @Value("${generic.api.url}")
    private String genericProductUrl;
//    @Autowired
//    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
//        this.restTemplateBuilder = restTemplateBuilder;
//    }
    @Autowired
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakestore.api.url}") String fakestoreurl){
        this.restTemplateBuilder = restTemplateBuilder;
        this.specificProductUrl = fakestoreurl;
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

    public FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDto.class, id);

        if(responseEntity.getBody() == null){
            //throw an Exception, So we should make our own exception to show the msg
            throw new ProductNotFoundException("Product Not found for id :" +id);
        }
        //return "Product Fetch From Fake Service. Id: " + id;
        return  responseEntity.getBody();
    }


    public FakeStoreProductDto[] getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(genericProductUrl, FakeStoreProductDto[].class);

//        List<Product> productList = new LinkedList<>();
//        for(FakeStoreProductDto fakeStoreProductDto: responseEntity.getBody()){
//            productList.add(getProductFromFakeStoreProductDto(fakeStoreProductDto));
//        }

        return responseEntity.getBody();
    }


    public FakeStoreProductDto deleteProductById(Long id) {
        // Prepare a RestTemplate instance to execute HTTP requests
        RestTemplate restTemplate = restTemplateBuilder.build();

        // Prepare a request callback to set the accept header for the HTTP request
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);

        // Prepare a response extractor to extract the response entity from the HTTP response
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        // Execute an HTTP DELETE request to the specific product URL with the provided ID
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(
                specificProductUrl,             // URL of the specific product to be deleted
                HttpMethod.DELETE,             // HTTP DELETE method
                requestCallback,               // Request callback to set accept header
                responseExtractor,             // Response extractor to extract response entity
                id                             // ID of the product to be deleted
        );

        // Convert the response body (deleted product) from FakeStoreProductDto to Product object
        FakeStoreProductDto deletedProduct = responseEntity.getBody();

        // Return the deleted product
        return deletedProduct;
    }

//    public Product deleteProductById(Long id) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        //restTemplate.delete(specificProductUrl, id);
//        //we get response in JSON format from http request below in requestCallback
//        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
//        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
//
//        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(specificProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
//
//        return getProductFromFakeStoreProductDto(responseEntity.getBody());
//    }


    public FakeStoreProductDto addProduct(FakeStoreProductDto fakeStoreProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity(genericProductUrl,
                fakeStoreProductDto, FakeStoreProductDto.class);

        return responseEntity.getBody();
    }


    public void updateProductById() {

    }

}
