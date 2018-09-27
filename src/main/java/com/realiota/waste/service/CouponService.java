package com.realiota.waste.service;

import com.realiota.waste.dto.CouponDTO;
import com.realiota.waste.entity.mysql.Coupon;

import java.util.List;

public interface CouponService {
    List<CouponDTO> getAllCoupons(Long phoneNumber);

    CouponDTO convert(Coupon coupon);
}
