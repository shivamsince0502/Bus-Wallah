package com.services;

import com.model.Booking;
import com.payload.BookingPayload;

public interface BookingService {
    Booking createBooking(BookingPayload bookingPayload);
}
