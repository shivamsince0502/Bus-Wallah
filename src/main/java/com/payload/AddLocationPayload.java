package com.payload;

import java.util.List;

public class AddLocationPayload {
    private List<String> locationNames;

    public AddLocationPayload(List<String> locationNames) {
        this.locationNames = locationNames;
    }
    public  AddLocationPayload(){}
    public List<String> getLocationNames() {
        return locationNames;
    }

    public void setLocationNames(List<String> locationNames) {
        this.locationNames = locationNames;
    }
}
