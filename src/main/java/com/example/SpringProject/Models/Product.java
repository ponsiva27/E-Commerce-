package com.example.SpringProject.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends  BaseModel {

    private String title;
    private int price;
    private String description;
    private String image;

    //every product has only one category
    //one category has many product
    @ManyToOne
    private Category category;


}
