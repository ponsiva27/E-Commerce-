package com.example.SpringProject.DTOs;

import com.example.SpringProject.Models.Name;
import com.example.SpringProject.Models.UserAddress;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRequestDTO {


    private String email;
    private String username;
    private String password;
    private Name name;
    private UserAddress Address;
    private String phone;

}
