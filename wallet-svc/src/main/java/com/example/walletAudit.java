package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;


public class walletAudit {

    private Integer Id;

    @ManyToOne
    private Wallet wallet;

    private Long balanceBefore;

    private Long balanceAfter;

    private String txnId;



}
