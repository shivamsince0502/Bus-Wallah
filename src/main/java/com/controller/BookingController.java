package com.controller;

import com.model.Booking;
import com.payload.BookingPayload;
import com.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/createbooking")
    private Booking createBooking(@RequestBody BookingPayload bookingPayload){
        return bookingService.createBooking(bookingPayload);
    }

}
