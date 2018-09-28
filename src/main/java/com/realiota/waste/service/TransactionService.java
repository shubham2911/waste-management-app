package com.realiota.waste.service;

import com.realiota.waste.dto.TransactionDTO;
import com.realiota.waste.entity.mysql.Transaction;
import com.realiota.waste.enums.TransactionType;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Long userId, BigDecimal amount, TransactionType transactionType, String remarks);

    List<TransactionDTO> getTransactions(Long phoneNumber, Long fromDate, Long toDate);

}
