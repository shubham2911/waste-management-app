package com.realiota.waste.entity.mysql;

import com.realiota.waste.entity.mysql.base.BaseEntity;
import com.realiota.waste.enums.TransactionType;
import com.realiota.waste.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "opening_balance")
    private BigDecimal opening_balance;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "closing_balance")
    private BigDecimal closing_balance;

}
