package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public void create(@RequestBody CreateUserRequest request) throws JsonProcessingException {
        this.userService.create(request);
    }

}
