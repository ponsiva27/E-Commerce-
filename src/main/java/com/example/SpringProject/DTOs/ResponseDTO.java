package com.example.SpringProject.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResponseDTO {
    private long id;
    private String title;
    private int price;
    private String description;
    private String Category;

}
