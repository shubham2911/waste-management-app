package com.realiota.waste.controller;

import com.realiota.waste.dto.CouponDTO;
import com.realiota.waste.dto.Response;
import com.realiota.waste.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response<CouponDTO> createCoupon(@RequestBody CouponDTO couponDTO) {
        return null;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response<List<CouponDTO>> getCoupons(@PathVariable(name = "userPhoneNumber") Long userPhoneNumber) {
        return new Response<>(couponService.getAllCoupons(userPhoneNumber));
    }
}