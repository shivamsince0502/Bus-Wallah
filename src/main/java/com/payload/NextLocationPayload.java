package com.payload;

import java.util.List;

public class NextLocationPayload {
    private String currLocation;
    private List<String> prevLocations;

    public NextLocationPayload(String currLocation) {
        this.currLocation = currLocation;
    }

    public NextLocationPayload() {
    }

    public String getCurrLocation() {
        return currLocation;
    }

    public void setCurrLocation(String currLocation) {
        this.currLocation = currLocation;
    }

    public List<String> getPrevLocations() {
        return prevLocations;
    }

    public void setPrevLocations(List<String> prevLocations) {
        this.prevLocations = prevLocations;
    }
}
