package com.realiota.waste.service;

import com.realiota.waste.dto.CouponDTO;
import com.realiota.waste.entity.mysql.Coupon;
import com.realiota.waste.entity.mysql.User;

import java.util.List;

public interface CouponService {

    List<CouponDTO> getAllCoupons(Long phoneNumber);

    CouponDTO createCoupon(CouponDTO couponDTO);

    Coupon convert(CouponDTO couponDTO, User user, Long usedCount);

    CouponDTO convertToCouponDTO(Coupon coupon, User user, String redemptionCode);

    CouponDTO redeemCoupon(Long phoneNumber, Long couponId);

    Coupon findById(Long couponId);
}
