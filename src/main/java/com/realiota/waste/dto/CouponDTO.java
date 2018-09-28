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
    private Long id;
    private String redemptionCode;
    private String createdBy;
    private Long userPhoneNumber;
    private Long validFrom;
    private Long validTo;
    private String title;
    private String description;
    private Long usedCount;
    private Long maxCount;
}
