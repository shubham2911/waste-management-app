package com.realiota.waste.repository.mysql;

import com.realiota.waste.entity.mysql.UserCouponMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCouponMappingRepository extends JpaRepository<UserCouponMapping, Long> {

    List<UserCouponMapping> findByUserIdAndCouponId(Long userId, Long couponId);
}
