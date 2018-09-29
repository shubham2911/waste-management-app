package com.realiota.waste.service;

import com.realiota.waste.dto.BookingDTO;
import com.realiota.waste.dto.LoginDTO;
import com.realiota.waste.dto.UserDTO;
import com.realiota.waste.entity.mysql.Booking;
import com.realiota.waste.entity.mysql.User;

import java.math.BigDecimal;

public interface BookingService {

    BookingDTO initiateBooking(BookingDTO bookingDTO);

    BookingDTO completeBooking(BookingDTO bookingDTO);

    BookingDTO convert(Booking booking);
}
