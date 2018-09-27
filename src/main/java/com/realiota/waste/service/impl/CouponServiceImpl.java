package com.realiota.waste.service.impl;

import com.realiota.waste.dto.CouponDTO;
import com.realiota.waste.entity.mysql.Coupon;
import com.realiota.waste.entity.mysql.User;
import com.realiota.waste.repository.mysql.CouponRepository;
import com.realiota.waste.service.CouponService;
import com.realiota.waste.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public List<CouponDTO> getAllCoupons(Long phoneNumber) {
        User user = userService.getByPhoneNumber(phoneNumber);
        List<Coupon> coupons = couponRepository.findByIssuedByUserId(user.getId());
        List<CouponDTO> couponDTOS = new ArrayList<>();
        for(Coupon coupon: coupons) {
            couponDTOS.add(convert(coupon));
        }
        return couponDTOS;
    }

    @Override
    public CouponDTO convert(Coupon coupon) {
        return new CouponDTO(coupon.getValidFrom(), coupon.getValidTo(), coupon.getTitle(), coupon.getDescription(), coupon.getUsedCount(), coupon.getMaxCount());
    }
}
