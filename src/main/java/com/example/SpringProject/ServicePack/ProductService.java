package com.example.SpringProject.ServicePack;

import com.example.SpringProject.Exception.ProductNotFoundException;
import com.example.SpringProject.Models.Product;
import com.example.SpringProject.Models.addProductDTO;

import java.util.List;

public interface ProductService {

    public Product getaProduct(long id) throws ProductNotFoundException;

    public List<Product> getAllProduct();

    public Product replaceProduct(long id, addProductDTO AddProduct);

    public List<Product> getaCategory(String id);

    public Product addNewProduct(addProductDTO  AddProduct);

    public Product updateProduct(long id, addProductDTO AddProduct) throws ProductNotFoundException;

    public boolean deleteProduct(long id);





}
