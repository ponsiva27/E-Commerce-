package com.example.SpringProject.Models;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User{

    private long id;
    private String email;

    private String username;
    private String password;

    private Name name;

    private UserAddress address;

    private String phone;


}
