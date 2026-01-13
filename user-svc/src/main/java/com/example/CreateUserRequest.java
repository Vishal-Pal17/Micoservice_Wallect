package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {




    private String name;

    private String email;


    private Integer age;

    private String mobile;

    public User toUser(){
        return User.builder()
                .name(this.name)
                .email(this.email)
                .age(this.age)
                .mobile(this.mobile)
                .build();
    }

}


