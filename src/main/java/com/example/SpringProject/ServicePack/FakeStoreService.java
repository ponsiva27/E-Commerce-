package com.example.SpringProject.ServicePack;

import com.example.SpringProject.Exception.ProductNotFoundException;
import com.example.SpringProject.Models.Category;
import com.example.SpringProject.Models.Product;
import com.example.SpringProject.Models.ResponseDTO;
import com.example.SpringProject.Models.addProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("FakeStoreService")
public class FakeStoreService implements  ProductService{

    RestTemplate restTemplate;

     @Autowired
    public FakeStoreService(RestTemplate restTemplate) {
         this.restTemplate = restTemplate;
    }

    @Override
    public Product getaProduct(long id) throws ProductNotFoundException {
        //long math =3/0;

        //hit the fake store API and get the response
        //pass the request and convert it Product class.
       ResponseDTO responseDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id, ResponseDTO.class
        );

       if(responseDTO==null){
           throw new ProductNotFoundException("Product with Id "+id+ "not found");
       }
       //converting the response to product object
        Product product = getProductfromResponse(responseDTO);

       return product;

    }
    @Override
    public List<Product> getAllProduct() {
      ResponseDTO[] responseList= restTemplate.getForObject
              ("https://fakestoreapi.com/products", ResponseDTO[].class);

      ArrayList<Product> productlist = new ArrayList<>();
       for(ResponseDTO responseDTO : responseList) {
           productlist.add(getProductfromResponse(responseDTO));
       }
            return productlist;
    }

    @Override
    public List<Product>  getaCategory(String id) {
      ResponseDTO[] responseList =  restTemplate.getForObject
              ("https://fakestoreapi.com/products/category/"+ id, ResponseDTO[].class);

            ArrayList<Product>  productList = new ArrayList<>();
            for(ResponseDTO responseDTO : responseList) {
                productList.add(getProductfromResponse(responseDTO));
            }
            return  productList;
    }

//    public List<Category> getAllCategory() {
//        String[] categoryNames = restTemplate.getForObject
//                    ("https://fakestoreapi.com/products/categories", String[].class);
//
//         ArrayList<CategoryResponseDTO>  categoriesList = new ArrayList<>();
//
//         for(String categoryName: categoryNames) {
//              CategoryResponseDTO cate = new CategoryResponseDTO();
//              cate.setName(categoryName);
//              categoriesList.add(cate);
//
//         }
//
//         return categoriesList;
//    }


    @Override //put method
    public Product replaceProduct(long id,  addProductDTO AddProduct) {
        RequestCallback requestcallback =
                restTemplate.httpEntityCallback(AddProduct, ResponseDTO.class);

        HttpMessageConverterExtractor<ResponseDTO>  responseExtractor  =
                new HttpMessageConverterExtractor(ResponseDTO.class,restTemplate.getMessageConverters());

        //In 1 call we can  add a product and get a response out of it.
      ResponseDTO responseDTO =  restTemplate.execute(
               "https://fakestoreapi.com/product" +id,
               HttpMethod.PUT,    //method put
              requestcallback,  //request information
               responseExtractor);  // response information

      return getProductfromResponse(responseDTO);
    }

    @Override
    public Product addNewProduct(addProductDTO AddProduct) {

        // Create an HTTP request entity with the request object as the body
       HttpEntity<addProductDTO> requestEntity = new HttpEntity<>(AddProduct);

        // Send the POST request to the server and expect a ResponseDTO in response
        ResponseEntity<ResponseDTO> responseEntity =
                restTemplate.exchange("https://fakestoreapi.com/products",
                   HttpMethod.POST,
                   requestEntity,
                   ResponseDTO.class);

        // Extract the response body from the ResponseEntity
          ResponseDTO responseDTO =  responseEntity.getBody();

         return getProductfromResponse(responseDTO);

    }

    @Override
    public Product updateProduct(long id, addProductDTO AddProduct) {

         HttpEntity<addProductDTO> requestEntity = new HttpEntity<>(AddProduct);

      ResponseEntity<ResponseDTO> responseDTO =  restTemplate.exchange("https://fakestoreapi.com/products/"+ id ,
                  HttpMethod.PATCH,
                  requestEntity,
                  ResponseDTO.class);

       ResponseDTO response = responseDTO.getBody();

       return getProductfromResponse(response);

    }
    
    
    @Override
    public boolean deleteProduct(long id) {
       ResponseEntity<Void> response =  restTemplate.exchange
                        ("https://fakestoreapi.com/products/"+ id,
                         HttpMethod.DELETE,
                        null, void.class);

          return response.getStatusCode().is2xxSuccessful();
    }

    public Product getProductfromResponse(ResponseDTO responseDTO) {
         Product product = new Product();
         product.setId(responseDTO.getId());
         product.setTitle(responseDTO.getTitle());
         product.setPrice(responseDTO.getPrice());
         product.setDescription(responseDTO.getDescription());
         product.setCategory(new Category());
         product.getCategory().setName(responseDTO.getCategory());

         return product;
    }

}
