package com.example;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {

    @Id
    private String id;

    private Integer userId;

    private Long balance;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Enumerated(value = EnumType.STRING)
    private WalletStatus status;

//    public static Snippet builder() {
//        return null;
//    }
}




