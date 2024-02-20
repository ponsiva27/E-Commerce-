package com.example.SpringProject.Controller;

import com.example.SpringProject.DTOs.UserRequestDTO;
import com.example.SpringProject.Models.User;
import com.example.SpringProject.ServicePack.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class UserController {

    UserService userService;

    @Autowired
    public  UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/{id}")
    public User getUser(@PathVariable("id") long id) {
        return userService.getUser(id);

    }

    @GetMapping("users")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("user")
    public User addUser(@RequestBody UserRequestDTO userRequestDTO) {

        return userService.addUser(userRequestDTO);
    }

    @DeleteMapping("users/{id}")
    public boolean deleteUser(@PathVariable("id")  long id) {
       return  userService.deleteUser(id);
    }
}
