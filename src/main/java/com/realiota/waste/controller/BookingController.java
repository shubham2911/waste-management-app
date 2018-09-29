package com.realiota.waste.controller;

import com.realiota.waste.dto.BookingDTO;
import com.realiota.waste.dto.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {

    @RequestMapping(value = "/initiate", method = RequestMethod.POST)
    public Response<BookingDTO> initiateBooking(@RequestBody BookingDTO bookingDTO) {
        return null;
    }

    @RequestMapping(value = "/complete", method = RequestMethod.GET)
    public Response<BookingDTO> completeBooking(@RequestBody BookingDTO bookingDTO) {
        return null;
    }
}