package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    KafkaTemplate<String , String> KafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public  void create(CreateUserRequest request)throws JsonProcessingException {
     User user =    request.toUser();
        this.userRepository.save(user);

        JSONObject obj = this.objectMapper.convertValue(user , JSONObject.class);

        KafkaTemplate.send("user-created" ,objectMapper.writeValueAsString(obj));


    }
}
