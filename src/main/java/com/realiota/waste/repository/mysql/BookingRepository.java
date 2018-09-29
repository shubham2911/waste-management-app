package com.realiota.waste.repository.mysql;

import com.realiota.waste.entity.mysql.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Booking findByBookingNumber(String bookingNumber);
}
