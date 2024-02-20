package com.example.SpringProject.Controller;


import com.example.SpringProject.Exception.ProductNotFoundException;
import com.example.SpringProject.Models.Category;
import com.example.SpringProject.Models.Product;
import com.example.SpringProject.Models.addProductDTO;
import com.example.SpringProject.ServicePack.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping()
public class ProductController {

    ProductService prodService;

    @Autowired
    public ProductController(@Qualifier("FakeStoreService")ProductService prodService) {

        this.prodService = prodService;
    }

    @GetMapping("productId/{id}")
    public String productID(@PathVariable("id") String id) {

        return "your Product ID is "+ id;

    }

    //get all the product details from the Third Party API
    @GetMapping("/products")
    public List<Product> getAllProducts() {

        return  prodService.getAllProduct();
    }

    //get a single product by Id from the Third Party API
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id")  long id) throws ProductNotFoundException {

          Product product = prodService.getaProduct(id);  //response

          HttpStatus status = HttpStatus.OK;   //status

          //returning the ResponseEntity with header,  responseBody and status
        ResponseEntity responseEntity = new ResponseEntity(product, status);

         return responseEntity;

    }

    @GetMapping("products/categories")
    public List<Product> getAllCategory() {
        return new ArrayList<>();
    }


    //Get all the products from a particular category
    @GetMapping("products/category/{categoryName}")
    public List<Product>  getCategory(@PathVariable("categoryName") String categoryName) {

        return  prodService.getaCategory(categoryName);

    }


    //Add a product to the API
    @PostMapping("/products")
    public Product  addProduct(@RequestBody addProductDTO product) {

        Product dbProduct  = new Product();
        dbProduct.setTitle(product.getTitle());
        dbProduct.setPrice(product.getPrice());
        dbProduct.setDescription(product.getDescription());
        dbProduct.setImage(product.getImage());
        dbProduct.setCategory(new Category());
        dbProduct.getCategory().setName(product.getCategory());

      Product savedProduct  = prodService.addNewProduct(product);

      return savedProduct;
    }

    //update a Product
    @PatchMapping("/products/{id}")
    public Product updateProduct( @PathVariable("id") long id , @RequestBody addProductDTO product ) throws ProductNotFoundException {
        return  prodService.updateProduct(id, product);
    }

    //replace a product
    @PutMapping("/products/{id}")
    public Product replaceProduct(@PathVariable("id") long id, @RequestBody addProductDTO product){
        Product dbProduct  = new Product();
        dbProduct.setTitle(product.getTitle());
        dbProduct.setPrice(product.getPrice());
        dbProduct.setDescription(product.getDescription());
        dbProduct.setImage(product.getImage());
        dbProduct.setCategory(new Category());
        dbProduct.getCategory().setName(product.getTitle());

      return null;
    }


    @DeleteMapping("/products/{id}")
    public boolean deleteProduct(@PathVariable("id") long id) {
         return prodService.deleteProduct(id);
    }

}
