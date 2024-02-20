package com.example.SpringProject.RepositoryPackage;

import com.example.SpringProject.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.*;

public interface ProductRepository extends JpaRepository <Product, Long> {

    //Query to find the product by its title


    @Query("select p from Product p where p.price < ?1")
    List<Product> getData(int price);


    @Override
    Optional<Product> findById(Long id);

    @Override //declared method
    Product save(Product product);

}
