package com.realiota.waste.repository.mysql;

import com.realiota.waste.entity.mysql.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserIdAndCreatedTimestampBetween(Long userId, Long fromDate, Long toDate);
}
