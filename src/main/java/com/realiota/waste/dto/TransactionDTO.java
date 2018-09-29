package com.realiota.waste.dto;

import com.realiota.waste.entity.mysql.base.BaseEntity;
import com.realiota.waste.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private TransactionType transactionType;
    private String remarks;
    private BigDecimal opening_balance;
    private BigDecimal amount;
    private BigDecimal closing_balance;
    private Long transactionDate;
}
