package com.example.SpringProject;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Testcontroller {
    @GetMapping("/print")
    public String  print() {
        return "Ponsiva , you created your First API";
    }

    @GetMapping("/add")
    public String addNum () {
        return "50+6";
    }

    @GetMapping("/details/{name}/{address}")
    public String details(@PathVariable("name") String name, @PathVariable("address") String address) {
         return "your name is"+ name+" "+ "Address is"+ address;
    }
}
