package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @Column(name = "driver_id")
    private int driverId;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "driver_mob")
    private String driverMob;

    @Column(name = "driver_email")
    private String driverEmail;

    @Column(name = "bus_id")
    private int busId;

    public Driver() {
    }

    public Driver(int driverId, String driverName, String driverMob, String driverEmail, int busId) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverMob = driverMob;
        this.driverEmail = driverEmail;
        this.busId = busId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverMob() {
        return driverMob;
    }

    public void setDriverMob(String driverMob) {
        this.driverMob = driverMob;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }
}
