package com.realiota.waste.repository.mysql;

import com.realiota.waste.entity.mysql.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    List<Coupon> findByIssuedByUserId(Long issuedByUserId);
}
