package com.realiota.waste.service.impl;

import com.realiota.waste.dto.TransactionDTO;
import com.realiota.waste.entity.mysql.Transaction;
import com.realiota.waste.entity.mysql.User;
import com.realiota.waste.enums.TransactionType;
import com.realiota.waste.repository.mysql.TransactionRepository;
import com.realiota.waste.service.TransactionService;
import com.realiota.waste.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    @Transactional
    public Transaction createTransaction(Long userId, BigDecimal amount, TransactionType transactionType, String remarks) {
        User user = userService.getByUserId(userId);
        BigDecimal openingBalance = user.getGreenMoneyBalance();
        BigDecimal closingBalance = openingBalance;
        switch (transactionType) {
            case DEBIT:
                closingBalance = openingBalance.subtract(amount);
                break;
            case CREDIT:
                closingBalance = openingBalance.add(amount);
                break;
        }
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setAmount(amount);
        transaction.setOpening_balance(openingBalance);
        transaction.setClosing_balance(closingBalance);
        transaction.setTransactionType(transactionType);
        transaction.setRemarks(remarks);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionDTO> getTransactions(Long phoneNumber, Long fromDate, Long toDate) {
        User user = userService.getByPhoneNumber(phoneNumber);
        List<Transaction> transactions = transactionRepository.findByUserIdAndCreatedTimestampBetween(user.getId(), fromDate, toDate);
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for(Transaction transaction : transactions) {
            transactionDTOS.add(convert(transaction));
        }
        return transactionDTOS;
    }

    TransactionDTO convert(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionType(transaction.getTransactionType());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setOpening_balance(transaction.getOpening_balance());
        transactionDTO.setClosing_balance(transaction.getClosing_balance());
        transactionDTO.setRemarks(transaction.getRemarks());
        transactionDTO.setTransactionDate(transaction.getCreatedTimestamp());
        return transactionDTO;
    }
}
