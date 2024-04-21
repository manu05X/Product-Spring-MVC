package com.example.productservicejanbatch.controller;


import com.example.productservicejanbatch.dtos.ExceptionDto;
import com.example.productservicejanbatch.exceptions.ProductNotFoundException;
import com.example.productservicejanbatch.models.Product;
import com.example.productservicejanbatch.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    // Dependency Injection => 1.Field Injection : This is not recomended to use
    //@Autowired

    //Time stamp 1:20
    //2.This is constructor Injection and it is recommended to use
    private ProductService productService;
    @Autowired
    public ProductController (@Qualifier("FakeProductService") ProductService productService){
        this.productService = productService;
    }

    /**3.Setter Injection -> not recommended . At runtime it gives nullpoint exception. for example we are making a cll before setting up the productService so at
    //compile time we will not get to know that we have not set the productService. So at runtime it give Nullptr exception.
    //Incomplete object state -> Can cause unexpected behavior or null pointer exceptions if an object is partially constructed and a setter is called*/
//    @Autowired
//    public void setProductService(ProductService productService){
//        this.productService = productService;
//    }

    //entity name should be in prular format
    //@GetMapping("/products/{id}") // as /products/ is common path
//    @GetMapping("/{id}")
//    public String getProductById(@PathVariable("id") Long id){
//        //@PathVariable("id") is use to map with the request parameter with Long id and Long id will have default NULL value
//        //return "Product fetch with id: "+ id;
//        return productService.getProductById(id);
//    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        //@PathVariable("id") is use to map with the request parameter with Long id and Long id will have default NULL value
        //return "Product fetch with id: "+ id;
        return productService.getProductById(id);
    }

    //@GetMapping("/products")
    @GetMapping()
    public List<Product> getAllProducts(){

        //return Collections.emptyList();
        //calling the service
        return productService.getAllProducts();
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return  productService.addProduct(product);
    }

    //@GetMapping("/{id}")
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        //@PathVariable("id") is use to map with the request parameter with mLong id and Long id will have default NULL value
        //return "Product fetch with id: "+ id;
        productService.deleteProductById(id);
    }

//    public String getProductByCategory(String category){
//
//    }

    //Method 1 : this is used prefered
//    @ExceptionHandler(ProductNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ResponseBody
//    private ExceptionDto handleProductNotFoundException(ProductNotFoundException e){
//        ExceptionDto exceptionDto = new ExceptionDto();
//        exceptionDto.setMessage(e.getMessage());
//        exceptionDto.setStatus("Failure");
//
//        return exceptionDto;
//    }

//    //Method 2
//    @ExceptionHandler(ProductNotFoundException.class)
//    private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException e){
//
//        ExceptionDto exceptionDto = new ExceptionDto();
//        exceptionDto.setMessage(e.getMessage());
//        exceptionDto.setStatus("Failure");
//
//        //Create obj of response entity
//        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
//
//        return responseEntity;
//    }

}

/**
* www.xyz.xom/api/........
* Endpoint -> Combination of domain Name + Path of Entity API
*
*/

/**
* 1. GetProductById(Id)
* 2. GetAllProducts
* 3. UpdateProductById()
* 4. DeleteProduct(id)
* 5. AddProduct()
* */

/**
 * Three Types of intection
 *
 * 1. Constructor Injection -> this is recommended to use. Constructor injection combines instantiation with injection. This constructor accepts class dependencies as parameters. Constructor Injection is a more reliable way of injecting dependencies.
 * 2. Field Injection -> Field injection is one of the easiest ways of injecting dependency in a class. A dependency can be injected using @Autowired (or @Inject or @Resource ) annotation.
 * 3. Setter Injection ->
 *
 */

/**
 * @ExceptionHandler() : This annotation provided by spring If any exception occour in controller please handles  it
 *  @ResponseStatus(HttpStatus.NOT_FOUND) -> use to give status as NOT_FOUND
 *     @ResponseBody -> Use to say that output of the below function should go in response body and status should be NOT_Found
 *
 *
 * As we are handling the Exception in ProductController ther for we are voilating SRP principal of SOLID
 * So we can add this exception handler into another class called as ControllerAdvice to avoid SRP
 */
