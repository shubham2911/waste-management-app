package com.realiota.waste.entity.mysql;

import com.realiota.waste.entity.mysql.base.BaseEntity;
import com.realiota.waste.enums.BookingPeriod;
import com.realiota.waste.enums.BookingStatus;
import com.realiota.waste.enums.BookingType;
import com.realiota.waste.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking extends BaseEntity {

    @Column(name = "booking_number")
    private String bookingNumber;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "booking_type")
    @Enumerated(EnumType.STRING)
    private BookingType bookingType;

    @Column(name = "booking_period")
    @Enumerated(EnumType.STRING)
    private BookingPeriod bookingPeriod;

    @Column(name = "starting_date")
    private Long startingDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "green_money_amount")
    private BigDecimal greenMoneyAmount;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @Column(name = "bookings_status")
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

}
