package com.payload;

public class BookingPayload {
    private int fare;
    private String start;
    private String end;
    private String routeName;
    private String busName;
    private String busNumber;
    private String userMob;
    private String userEmail;

    public BookingPayload() {
    }

    public BookingPayload(int fare, String start, String end, String routeName, String busName, String busNumber, String userMob, String userEmail) {
        this.fare = fare;
        this.start = start;
        this.end = end;
        this.routeName = routeName;
        this.busName = busName;
        this.busNumber = busNumber;
        this.userMob = userMob;
        this.userEmail = userEmail;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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

    public String getUserMob() {
        return userMob;
    }

    public void setUserMob(String userMob) {
        this.userMob = userMob;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
