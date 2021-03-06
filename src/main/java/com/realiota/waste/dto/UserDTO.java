package com.realiota.waste.dto;


import com.realiota.waste.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    String name;
    Long phoneNumber;
    UserType userType;
    BigDecimal greenMoneyBalance;
}
