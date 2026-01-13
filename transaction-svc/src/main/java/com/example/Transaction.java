package com.example;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String externalTxnId;

    private Long amount;

    private String  reason;

    private Integer sender;

    private Integer receiver;

    @Enumerated(EnumType.STRING)
   // @Column(name = "transaction_status")
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date createAt;

    @UpdateTimestamp
    private Date updateAt;

    private String txnFailureReason;





}
