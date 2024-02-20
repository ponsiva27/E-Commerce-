package com.example.SpringProject.RepositoryPackage;

import com.example.SpringProject.Models.Category;
import com.example.SpringProject.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository <Category, Long> {



    Optional<Product> findByName(String categoryName);


    @Override
    Optional<Category> findById(Long id);

    @Override
     Category save(Category category);
}
