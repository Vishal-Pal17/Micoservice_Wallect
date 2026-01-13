package com.example;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Entity
@Getter
@Setter
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

    private String email;

    private Integer age;

    private String mobile;

    @CreationTimestamp
    private Date createAt;

    @UpdateTimestamp
    private Date updatedAt;


}
