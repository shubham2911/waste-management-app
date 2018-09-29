package com.realiota.waste.service.impl;

import com.realiota.waste.dto.BookingDTO;
import com.realiota.waste.entity.mysql.Booking;
import com.realiota.waste.entity.mysql.User;
import com.realiota.waste.enums.BookingPeriod;
import com.realiota.waste.enums.BookingStatus;
import com.realiota.waste.enums.BookingType;
import com.realiota.waste.exception.WasteManagementException;
import com.realiota.waste.repository.mysql.BookingRepository;
import com.realiota.waste.service.BookingService;
import com.realiota.waste.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    @Transactional
    public BookingDTO initiateBooking(BookingDTO bookingDTO) {
        validateBookingDTO(bookingDTO);
        User user = userService.getByPhoneNumber(bookingDTO.getUserPhoneNumber());
        Booking booking = new Booking();
        booking.setBookingNumber(generateNewBookingNumber());
        booking.setBookingPeriod(bookingDTO.getBookingPeriod());
        booking.setBookingStatus(BookingStatus.INITIATED);
        booking.setBookingType(bookingDTO.getBookingType());
        booking.setStartingDate(booking.getStartingDate());
        booking.setAmount(getPrice(bookingDTO.getBookingType(), bookingDTO.getBookingPeriod()));
        booking.setUserId(user.getId());
        return convert(bookingRepository.save(booking));
    }

    @Override
    @Transactional
    public BookingDTO completeBooking(BookingDTO bookingDTO) {
        validateBookingDTO(bookingDTO);
        User user = userService.getByPhoneNumber(bookingDTO.getUserPhoneNumber());
        if (bookingDTO.getBookingNumber() == null) {
            throw new WasteManagementException("Cannot complete transaction without booking number");
        }
        if (bookingDTO.getGreenMoneyAmount() != null && bookingDTO.getGreenMoneyAmount().compareTo(user.getGreenMoneyBalance()) > 0) {
            throw new WasteManagementException("Green money insufficient");
        }
        Booking booking = bookingRepository.findByBookingNumber(bookingDTO.getBookingNumber());
        if (booking.getAmount().compareTo(bookingDTO.getAmount()) != 0) {
            throw new WasteManagementException("Total amount does not match");
        }
        booking.setGreenMoneyAmount(bookingDTO.getGreenMoneyAmount());
        booking.setPaymentAmount(bookingDTO.getPaymentAmount());
        booking.setBookingStatus(BookingStatus.BOOKED);
        return convert(bookingRepository.save(booking));
    }

    @Override
    public BookingDTO convert(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingNumber(booking.getBookingNumber());
        bookingDTO.setBookingPeriod(booking.getBookingPeriod());
        bookingDTO.setBookingStatus(booking.getBookingStatus());
        bookingDTO.setBookingType(booking.getBookingType());
        bookingDTO.setStartingDate(booking.getStartingDate());
        bookingDTO.setAmount(booking.getAmount());
        bookingDTO.setGreenMoneyAmount(booking.getGreenMoneyAmount());
        bookingDTO.setPaymentAmount(booking.getPaymentAmount());
        return bookingDTO;
    }

    private void validateBookingDTO(BookingDTO bookingDTO) {
        if (bookingDTO.getUserPhoneNumber() == null) {
            throw new WasteManagementException("User not found to create booking");
        }
        if (bookingDTO.getBookingType() == null) {
            throw new WasteManagementException("Booking Type required");
        }
        if (bookingDTO.getBookingPeriod() == null) {
            throw new WasteManagementException("Booking Period required");
        }
        if (bookingDTO.getStartingDate() == null || bookingDTO.getStartingDate().compareTo(DateTime.now().getMillis()) > 0) {
            throw new WasteManagementException("Invalid Booking Start Date");
        }
    }

    private BigDecimal getPrice(BookingType bookingType, BookingPeriod bookingPeriod) {
        switch (bookingType) {
            case SCRAP_PICKUP:
                switch (bookingPeriod) {
                    case DAY:
                        return new BigDecimal(20);
                    case WEEK:
                    case FORT_NIGHT:
                    case MONTH:
                    case QUARTER:
                    case HALF_YEAR:
                    case YEAR:
                        throw new WasteManagementException("Not allowed.");
                }
                break;
            case WASTE_COLLECTION:
                switch (bookingPeriod) {
                    case DAY:
                        throw new WasteManagementException("Not allowed.");
                    case WEEK:
                        return new BigDecimal(80);
                    case FORT_NIGHT:
                        return new BigDecimal(150);
                    case MONTH:
                        return new BigDecimal(250);
                    case QUARTER:
                        return new BigDecimal(900);
                    case HALF_YEAR:
                        return new BigDecimal(1600);
                    case YEAR:
                        return new BigDecimal(3000);
                }
                break;
        }
        throw new WasteManagementException("Price not available");
    }

    private String generateNewBookingNumber() {
        return "BK" + UUID.randomUUID().toString().replaceAll("-", "").toUpperCase().substring(0, 8);
    }
}
