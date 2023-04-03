package com.payload;

public class AddDriverPayload {
    private String driverName;
    private String driverNumber;
    private String driverEmail;
    private String busName;
    public AddDriverPayload() {
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public AddDriverPayload(String driverName, String driverNumber, String driverEmail, String busName) {
        this.driverName = driverName;
        this.driverNumber = driverNumber;
        this.driverEmail = driverEmail;
        this.busName = busName;
    }
}
