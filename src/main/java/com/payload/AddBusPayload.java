package com.payload;

public class AddBusPayload {
    private String busName;
    private String busNumber;
    private String busActive;
    private String routeName;
    private int noOfSeats;
    public AddBusPayload(){}
    public AddBusPayload(String busName, String busActive, String routeName, int noOfSeats, String busNumber) {
        this.busName = busName;
        this.busActive = busActive;
        this.routeName = routeName;
        this.noOfSeats = noOfSeats;
        this.busNumber = busNumber;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusActive() {
        return busActive;
    }

    public void setBusActive(String busActive) {
        this.busActive = busActive;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }
}
