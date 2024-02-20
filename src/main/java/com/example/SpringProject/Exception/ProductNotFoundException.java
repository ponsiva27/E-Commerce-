package com.example.SpringProject.Exception;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
