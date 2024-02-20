package com.example.SpringProject.ServicePack;

import com.example.SpringProject.DTOs.UserRequestDTO;
import com.example.SpringProject.DTOs.UserResponseDTO;
import com.example.SpringProject.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeUserService implements  UserService{


    RestTemplate restTemplate;

    @Autowired
    public FakeUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public User getUser(long id) {
       UserResponseDTO userResponseDTO = restTemplate.getForObject
                                        ("https://fakestoreapi.com/users/"+ id,
                                        UserResponseDTO.class);

       User user = getUserfromResponse(userResponseDTO);

       return user;

    }

    @Override
    public List<User> getAllUser() {
       UserResponseDTO[] userResponse =  restTemplate.getForObject(
                                    "https://fakestoreapi.com/users",
                                        UserResponseDTO[].class);
       ArrayList<User>  list = new ArrayList<>();

       for(UserResponseDTO userlist : userResponse) {
           list.add(getUserfromResponse(userlist));
       }

       return list;
    }

    @Override
    public boolean deleteUser(long id) {
        ResponseEntity<Void> response = restTemplate.exchange(
                "https://fakestoreapi.com/users/" + id,
                   HttpMethod.DELETE,
                null,
                void.class);

        return response.getStatusCode().is2xxSuccessful();
    }


    @Override
    public User addUser(UserRequestDTO userRequestDTO) {

        HttpEntity<UserRequestDTO>  requestEntity  = new HttpEntity<>(userRequestDTO);

        ResponseEntity<UserResponseDTO> responseEntity =  restTemplate.exchange("https://fakestoreapi.com/users",
                HttpMethod.POST,
                requestEntity,
                UserResponseDTO.class);

          UserResponseDTO userResponse =  responseEntity.getBody();

          return getUserfromResponse(userResponse);
    }

    public User getUserfromResponse(UserResponseDTO userResponseDTO) {
         User user = new User();
         user.setId(userResponseDTO.getId());
         user.setEmail(userResponseDTO.getEmail());
         user.setUsername(userResponseDTO.getUsername());
         user.setPassword(userResponseDTO.getPassword());
         user.setName(userResponseDTO.getName());
         user.setAddress(userResponseDTO.getAddress());
         user.setPhone(userResponseDTO.getPhone());

         return user;
    }
}
