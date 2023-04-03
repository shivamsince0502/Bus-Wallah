package com.services;

import com.model.Booking;
import com.model.Driver;
import com.model.Location;
import com.payload.BookingPayload;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class BookingServicesImpl implements BookingService{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Booking createBooking(BookingPayload bookingPayload) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String ssql = "select * from location where location_name = '"+bookingPayload.getStart()+"'";
        SQLQuery query = session.createSQLQuery(ssql);
        query.addEntity(Location.class);
        Location start = (Location) query.uniqueResult();
        String esql = "select * from location where location_name = '"+bookingPayload.getEnd()+"'";
        SQLQuery equery = session.createSQLQuery(esql);
        equery.addEntity(Location.class);
        Location end = (Location) equery.uniqueResult();
        Booking booking = new Booking();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        booking.setDateOfBooking(timestamp);
        booking.setBusName(bookingPayload.getBusName());
        booking.setBusNumber(bookingPayload.getBusNumber());
        booking.setRouteName(bookingPayload.getRouteName());
        booking.setFare(bookingPayload.getFare());
        booking.setStartId(start.getLocationId());
        booking.setEndId(end.getLocationId());
        String dsql = "select * from driver where bus_id = (select bus_id from bus where bus_number = '"+bookingPayload.getBusNumber()+"')";
        SQLQuery dquery = session.createSQLQuery(dsql);
        dquery.addEntity(Driver.class);
        Driver driver = (Driver) dquery.uniqueResult();
        if(bookingPayload.getUserEmail() != null) booking.setUserEmail(bookingPayload.getUserEmail());
        else booking.setUserEmail("");
        if(bookingPayload.getUserMob() != null) booking.setUserMobile(bookingPayload.getUserMob());
        else booking.setUserMobile(bookingPayload.getUserMob());

        session.save(booking);
        transaction.commit();
        session.close();
        Booking booking1 = new Booking();
        booking1.setBusNumber(driver.getDriverMob());
        booking1.setBusName(driver.getDriverEmail());

        return booking1;
    }
}
