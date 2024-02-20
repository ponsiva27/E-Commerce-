package com.example.SpringProject;


import com.example.SpringProject.Models.Product;
import com.example.SpringProject.RepositoryPackage.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class SpringProjectApplicationTests {

	ProductRepository productRepository;

	@Autowired
	public SpringProjectApplicationTests (ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Test
	void contextLoads() {
	}

	@Test
	void getAProduct() {
	   List<Product> productList = productRepository.getData(25000);

	    for(Product p : productList) {
			System.out.println("Product value less than 25k is"+ p.getPrice());
		}

	}

}
