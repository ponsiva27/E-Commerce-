package com.example.SpringProject.ServicePack;

import com.example.SpringProject.DTOs.UserRequestDTO;
import com.example.SpringProject.Models.User;

import java.util.List;

public interface UserService {

    public User getUser(long id);

    public List<User> getAllUser();

    public boolean deleteUser(long id);

    public User addUser(UserRequestDTO userRequestDTO);


}
