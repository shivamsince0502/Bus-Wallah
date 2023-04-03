package com.payload;

import com.model.Bus;
import com.model.Route;

import java.util.List;

public class BusRoutesPayload {
    private Bus bus;
    private Route route;
    private List<String> pathLocations;
    private int totalDistance;

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<String> getPathLocations() {
        return pathLocations;
    }

    public void setPathLocations(List<String> pathLocations) {
        this.pathLocations = pathLocations;
    }

    public BusRoutesPayload(){}
    public BusRoutesPayload(Bus bus, Route route, List<String> pathLocations, int totalDistance) {
        this.bus = bus;
        this.route = route;
        this.pathLocations = pathLocations;
        this.totalDistance = totalDistance;
    }

    @Override
    public String toString() {
        return "BusRoutesPayload{" +
                "bus=" + bus +
                ", route=" + route +
                ", pathLocations=" + pathLocations +
                ", totalDistance=" + totalDistance +
                '}';
    }
}
