package com.realiota.waste.controller;

import com.realiota.waste.dto.CouponDTO;
import com.realiota.waste.dto.Response;
import com.realiota.waste.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response<CouponDTO> createCoupon(@RequestBody CouponDTO couponDTO) {
        return new Response<>(couponService.createCoupon(couponDTO));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response<List<CouponDTO>> getCoupons(@RequestParam(name = "userPhoneNumber") Long userPhoneNumber) {
        return new Response<>(couponService.getAllCoupons(userPhoneNumber));
    }

    @RequestMapping(value = "/redeem", method = RequestMethod.POST)
    public Response<CouponDTO> createCoupon(@RequestParam("requestedBy") Long phoneNumber, @RequestParam("couponId") Long couponId) {
        return new Response<>(couponService.redeemCoupon(phoneNumber, couponId));
    }
}