package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bus")
public class Bus {

    @Id
    @Column(name = "bus_id")
    private int busId;

    @Column(name = "bus_name")
    private String busName;

    @Column(name = "bus_number")
    private String busNumber;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "route_id")
    private int routeId;

    @Column(name = "no_of_seats")
    private int noOfSeats;

    public Bus(){}
    public Bus(int busId, String busName, String busNumber, Boolean isActive, int routeId, int noOfSeats) {
        this.busId = busId;
        this.busName = busName;
        this.busNumber = busNumber;
        this.isActive = isActive;
        this.routeId = routeId;
        this.noOfSeats = noOfSeats;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busId=" + busId +
                ", busName='" + busName + '\'' +
                ", busNumber='" + busNumber + '\'' +
                ", isActive=" + isActive +
                ", routeId=" + routeId +
                ", noOfSeats=" + noOfSeats +
                '}';
    }
}
