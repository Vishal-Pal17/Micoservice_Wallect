package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class CommonConfig {


    @Bean
    public ObjectMapper getMapper(){
        return new ObjectMapper();
    }
}
