package com.realiota.waste.dto;


import com.realiota.waste.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CouponDTO {
    Long id;
    String redemptionCode;
    String createdBy;
    Long userPhoneNumber;
    Long validFrom;
    Long validTo;
    String title;
    String description;
    Long usedCount;
    Long maxCount;
}
