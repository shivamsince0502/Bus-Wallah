package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "booking")
public class Booking {
//    booking_id int primary key auto_increment,
//    user_mob int ,
//    user_email varchar(655),
//    route_name varchar(300) not null,
//    bus_name varchar(300) not null,
//    bus_number varchar(300) not null,
//    start_id int not null,
//    end_id int not null,
//    date_of_booking datetime not null,
//    fare int not null,
    @Id
    @Column(name = "booking_id")
    private int bookingId;

    @Column(name = "user_mob")
    private String userMobile;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "route_name")
    private String routeName;

    @Column(name = "bus_name")
    private String busName;

    @Column(name = "bus_number")
    private String busNumber;

    @Column(name = "start_id")
    private int startId;

    @Column(name = "end_id")
    private int endId;

    @Column(name = "date_of_booking")
    private Timestamp dateOfBooking;

    @Column(name = "fare")
    private int fare;

    public Booking() {
    }

    public Booking(int bookingId, String userMobile, String userEmail, String routeName, String busName, String busNumber, int startId, int endId, Timestamp dateOfBooking, int fare) {
        this.bookingId = bookingId;
        this.userMobile = userMobile;
        this.userEmail = userEmail;
        this.routeName = routeName;
        this.busName = busName;
        this.busNumber = busNumber;
        this.startId = startId;
        this.endId = endId;
        this.dateOfBooking = dateOfBooking;
        this.fare = fare;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public int getStartId() {
        return startId;
    }

    public void setStartId(int startId) {
        this.startId = startId;
    }

    public int getEndId() {
        return endId;
    }

    public void setEndId(int endId) {
        this.endId = endId;
    }

    public Timestamp getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Timestamp dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }
}
