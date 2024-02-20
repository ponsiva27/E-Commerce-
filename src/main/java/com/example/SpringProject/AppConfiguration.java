package com.example.SpringProject;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

   private int timeout;

    @Bean
    public RestTemplate createRestTemplate() {

         return  new RestTemplateBuilder().build();
    }



}
