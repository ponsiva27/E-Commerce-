package com.example.SpringProject.ServicePack;

import com.example.SpringProject.Exception.ProductNotFoundException;
import com.example.SpringProject.Models.Product;
import com.example.SpringProject.Models.addProductDTO;
import com.example.SpringProject.RepositoryPackage.CategoryRepository;
import com.example.SpringProject.RepositoryPackage.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("SelfStoreService")
public class SelfStoreService implements ProductService{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;


    @Autowired
    public SelfStoreService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product getaProduct(long id) throws ProductNotFoundException {
        Optional<Product> productID = productRepository.findById(id);

        if(productID.isEmpty()) {
            throw new ProductNotFoundException("Product with id" +id+ "doesn't exist");
        }
        Product productid =  productID.get();

        return productid;
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }

    @Override
    public Product replaceProduct(long id, addProductDTO AddProduct) {
        return null;
    }

    @Override
    public List<Product> getaCategory(String id) {
        return null;
    }

    @Override
    public Product addNewProduct(addProductDTO AddProduct) {
        //When adding a new Product it could be adding to the new Category or to the existing category
         /* Optional<Category> categoryName =   categoryRepository.findByName(AddProduct.getCategory().getName());
          Category savedCategory;
          if(categoryName.isEmpty()) {
            Category category =  new Category();
            category.setName(AddProduct.getCategory().getName());
            savedCategory =categoryRepository.save(category);
          } else {
              savedCategory = categoryName.get();
          }
          AddProduct.setCategory(savedCategory.getName());
        Product savedProduct =  productRepository.save(product);*/

          return null;

          
    }

    @Override
    public Product updateProduct(long id, addProductDTO AddProduct) throws ProductNotFoundException {

        Optional<Product> productobject = productRepository.findById(id);
        if(productobject.isEmpty()) {
            throw new ProductNotFoundException("Product with the given ID"+ id+ "doesn't exist to update the product");
        }

        Product existingProduct = productobject.get();

        Product ProductTobeSaved = new Product();

        if(AddProduct.getTitle()!=null) {
            ProductTobeSaved.setTitle(AddProduct.getTitle());
        } else {
            ProductTobeSaved.setTitle(existingProduct.getTitle());
        }


        if(AddProduct.getPrice()==0) {
            ProductTobeSaved.setPrice(AddProduct.getPrice());
        } else {
            ProductTobeSaved.setPrice(existingProduct.getPrice());
        }

//        if(AddProduct.getCategory().getName()==null) {
//
//        } else {
//            ProductTobeSaved.setCategory(existingProduct.getCategory());
//        }

        ProductTobeSaved.setDescription(
                AddProduct.getDescription() !=null ?
                        AddProduct.getDescription() : existingProduct.getDescription());

        ProductTobeSaved.setId(id);

        return productRepository.save(ProductTobeSaved);
    }

    @Override
    public boolean deleteProduct(long id) {
        return false;
    }
}
