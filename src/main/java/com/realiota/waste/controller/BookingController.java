package com.realiota.waste.controller;

import com.realiota.waste.dto.BookingDTO;
import com.realiota.waste.dto.Response;
import com.realiota.waste.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/initiate", method = RequestMethod.POST)
    public Response<BookingDTO> initiateBooking(@RequestBody BookingDTO bookingDTO) {
        return new Response<>(bookingService.initiateBooking(bookingDTO));
    }

    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    public Response<BookingDTO> completeBooking(@RequestBody BookingDTO bookingDTO) {
        return new Response<>(bookingService.completeBooking(bookingDTO));
    }
}