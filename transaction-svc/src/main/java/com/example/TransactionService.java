package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.lettuce.core.json.JsonObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;


@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(Transaction.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private KafkaTemplate<String, String> KafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    private JSONParser parser = new JSONParser();


    public String initiateTxn(Integer sender, Integer receiver, Long amount, String reason) throws JsonProcessingException {
        Transaction transaction = Transaction.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .transactionStatus(TransactionStatus.PENDING)
                .amount(amount)
                .reason(reason)
                .sender(sender)
                .receiver(receiver)
                .build();
        transactionRepository.save(transaction);
        JsonObject obj = this.objectMapper.convertValue(transaction, JsonObject.class);
        this.KafkaTemplate.send("transaction-topic", this.objectMapper.writeValueAsString(obj));

        return transaction.getExternalTxnId();

    }

    @KafkaListener(topics = "wallet-updates", groupId = "vishal")
    public void complete(String msg) throws ParseException {
        this.logger.info("completeTxn: received msg = {}", msg);
        JSONObject object = (JSONObject) this.parser.parse(msg);

        String externalTxnId = (String) object.get("externalTxnId");
        String walletUpdateStatus = (String) object.get("status");

        Transaction transaction = this.transactionRepository.findByExternalTxnId(externalTxnId);
        logger.info("completeTxn: transaction{}", transaction);
        if (!transaction.getTransactionStatus().equals(TransactionStatus.PENDING)) {
            this.logger.warn("transaction already reached terminal state, id - {}", externalTxnId);

            return;

        }
        TransactionStatus transactionStatus = walletUpdateStatus.equals("SUCCESS") ?
                TransactionStatus.SUCCESS :
                TransactionStatus.FAILED;

        transaction.setTransactionStatus(transactionStatus);

        this.transactionRepository.save(transaction);

    }

}