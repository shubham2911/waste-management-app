package com.realiota.waste.dto;


import com.realiota.waste.enums.BookingPeriod;
import com.realiota.waste.enums.BookingStatus;
import com.realiota.waste.enums.BookingType;
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
public class BookingDTO {
    String bookingNumber;
    Long userPhoneNumber;
    BookingType bookingType;
    BookingPeriod bookingPeriod;
    Long startingDate;
    BookingStatus bookingStatus;
    BigDecimal amount;
    BigDecimal greenMoneyAmount;
    BigDecimal paymentAmount;
}
