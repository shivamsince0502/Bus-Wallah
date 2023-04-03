package com.payload;

public class RoutesShowPayload {
    private String routeName;
    private String startLocation;
    private String endLocation;
    private int noOfStations;
    private int noOfBuses;

    public RoutesShowPayload() {
    }

    public RoutesShowPayload(String routeName, String startLocation, String endLocation, int noOfStations, int noOfBuses) {
        this.routeName = routeName;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.noOfStations = noOfStations;
        this.noOfBuses = noOfBuses;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public int getNoOfStations() {
        return noOfStations;
    }

    public void setNoOfStations(int noOfStations) {
        this.noOfStations = noOfStations;
    }

    public int getNoOfBuses() {
        return noOfBuses;
    }

    public void setNoOfBuses(int noOfBuses) {
        this.noOfBuses = noOfBuses;
    }
}
