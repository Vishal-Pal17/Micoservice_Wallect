package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/treasation")
    public String initiateTxn(@RequestParam("sender") Integer sender,
                              @RequestParam("receiver") Integer receiver,
                              @RequestParam("amount") Long amount ,
                              @RequestParam("reason") String reason) throws JsonProcessingException {
      return   this.transactionService.initiateTxn(sender,receiver,amount,reason);
    }
}
