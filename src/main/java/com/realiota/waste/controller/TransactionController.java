package com.realiota.waste.controller;

import com.realiota.waste.dto.Response;
import com.realiota.waste.dto.TransactionDTO;
import com.realiota.waste.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Response<List<TransactionDTO>> getTransactionsBetween(@RequestParam Long phoneNumber, @RequestParam Long fromDate, @RequestParam Long toDate) {
        return new Response<>(transactionService.getTransactions(phoneNumber, fromDate, toDate));
    }
}