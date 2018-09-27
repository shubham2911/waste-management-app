package com.realiota.waste.dto;


import com.realiota.waste.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponDTO {
    Long validFrom;
    Long validTo;
    String title;
    String description;
    Long usedCount;
    Long maxCount;
}
