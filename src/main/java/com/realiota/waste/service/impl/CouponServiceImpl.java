package com.realiota.waste.service.impl;

import com.realiota.waste.dto.CouponDTO;
import com.realiota.waste.entity.mysql.Coupon;
import com.realiota.waste.entity.mysql.User;
import com.realiota.waste.entity.mysql.UserCouponMapping;
import com.realiota.waste.enums.UserType;
import com.realiota.waste.exception.WasteManagementException;
import com.realiota.waste.repository.mysql.CouponRepository;
import com.realiota.waste.repository.mysql.UserCouponMappingRepository;
import com.realiota.waste.service.CouponService;
import com.realiota.waste.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CouponServiceImpl implements CouponService {

    private static final int REDEMPTION_CODE_STRING_LENGTH = 5;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserCouponMappingRepository userCouponMappingRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public List<CouponDTO> getAllCoupons(Long phoneNumber) {
        User user = userService.getByPhoneNumber(phoneNumber);
        List<Coupon> coupons = new ArrayList<>();
        switch (user.getUserType()) {
            case BUSINESS:
                coupons = couponRepository.findByIssuedByUserId(user.getId());
                break;
            case HOUSEHOLD:
                coupons = couponRepository.findAll();
                break;
        }
        List<CouponDTO> couponDTOS = new ArrayList<>();
        for (Coupon coupon : coupons) {
            // TODO dont make db calls for all coupons
            User issuedByUser = userService.getByUserId(coupon.getIssuedByUserId());
            List<UserCouponMapping> userCouponMappings = userCouponMappingRepository.findByUserIdAndCouponId(user.getId(), coupon.getId());
            String redemptionCode = null;
            if(!CollectionUtils.isEmpty(userCouponMappings)) {
                redemptionCode = userCouponMappings.get(0).getRedemptionCode();
            }
            CouponDTO couponDTO = convertToCouponDTO(coupon, issuedByUser, redemptionCode);
            couponDTO.setUserPhoneNumber(null);
            couponDTOS.add(couponDTO);
        }
        return couponDTOS;
    }

    @Override
    @Transactional
    public CouponDTO createCoupon(CouponDTO couponDTO) {
        log.info("Create coupon request : {}", couponDTO);
        User user = userService.getByPhoneNumber(couponDTO.getUserPhoneNumber());
        validateCreateCouponRequeest(couponDTO);
        Coupon coupon = convert(couponDTO, user, 0L);
        couponRepository.save(coupon);
        return convertToCouponDTO(coupon, user, null);
    }

    @Override
    public Coupon convert(CouponDTO couponDTO, User user, Long usedCount) {
        Coupon coupon = new Coupon();
        coupon.setTitle(couponDTO.getTitle());
        coupon.setDescription(couponDTO.getDescription());
        coupon.setIssuedByUserId(user.getId());
        coupon.setUsedCount(usedCount);
        coupon.setMaxCount(couponDTO.getMaxCount());
        coupon.setValidFrom(couponDTO.getValidFrom());
        coupon.setValidTo(couponDTO.getValidTo());
        return coupon;
    }

    @Override
    public CouponDTO convertToCouponDTO(Coupon coupon, User user, String redemptionCode) {
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setCreatedBy(user.getName());
        couponDTO.setRedemptionCode(redemptionCode);
        couponDTO.setId(coupon.getId());
        couponDTO.setTitle(coupon.getTitle());
        couponDTO.setDescription(coupon.getDescription());
        couponDTO.setUserPhoneNumber(user.getPhoneNumber());
        couponDTO.setUsedCount(coupon.getUsedCount());
        couponDTO.setMaxCount(coupon.getMaxCount());
        couponDTO.setValidFrom(coupon.getValidFrom());
        couponDTO.setValidTo(coupon.getValidTo());
        return couponDTO;
    }

    @Override
    @Transactional
    public CouponDTO redeemCoupon(Long phoneNumber, Long couponId) {
        Coupon coupon  = findById(couponId);
        User user = userService.getByPhoneNumber(phoneNumber);
        validateRedeemCouponRequeest(coupon, user);
        UserCouponMapping userCouponMapping = new UserCouponMapping();
        userCouponMapping.setCouponId(coupon.getId());
        userCouponMapping.setUserId(user.getId());
        userCouponMapping.setRedemptionCode(generateRandomRedemptionString());
        coupon.setUsedCount(coupon.getUsedCount() + 1);
        userCouponMappingRepository.save(userCouponMapping);
        couponRepository.save(coupon);
        return convertToCouponDTO(coupon, user, userCouponMapping.getRedemptionCode());
    }

    @Override
    public Coupon findById(Long couponId) {
        Optional<Coupon> optional = couponRepository.findById(couponId);
        if (Optional.empty().equals(optional)) {
            throw new WasteManagementException("Coupon not found.");
        }
        return optional.get();
    }

    private void validateCreateCouponRequeest(CouponDTO couponDTO) {
        if (couponDTO.getUserPhoneNumber() == null) {
            throw new WasteManagementException("User not found");
        }
        if (couponDTO.getValidFrom() == null || couponDTO.getValidTo() == null) {
            throw new WasteManagementException("From and To dates cannot be empty");
        }
        if (couponDTO.getTitle() == null) {
            throw new WasteManagementException("Title cannot be empty");
        }
        if (couponDTO.getDescription() == null) {
            throw new WasteManagementException("Description cannot be empty");
        }
    }

    private void validateRedeemCouponRequeest(Coupon coupon, User user) {
        if(!UserType.HOUSEHOLD.equals(user.getUserType())) {
            throw new WasteManagementException("Only household users can redeem coupons");
        }
        if (coupon.getUsedCount().equals(coupon.getMaxCount())) {
            throw new WasteManagementException("Maximum redemption limit reached");
        }
        Long now = DateTime.now().getMillis();
        if (now.compareTo(coupon.getValidFrom()) < 0 || now.compareTo(coupon.getValidTo()) > 0) {
            throw new WasteManagementException("Redemption Time does not match current time");
        }
        List<UserCouponMapping> userCouponMappings = userCouponMappingRepository.findByUserIdAndCouponId(user.getId(), coupon.getId());
        if(!CollectionUtils.isEmpty(userCouponMappings)) {
            throw new WasteManagementException("Can redeem a coupon only once");
        }
    }

    private String generateRandomRedemptionString() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, REDEMPTION_CODE_STRING_LENGTH);
    }
}
