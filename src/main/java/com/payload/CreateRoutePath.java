package com.payload;

import java.util.List;

public class CreateRoutePath {
    private String routeName;
    private List<String> cities;

    public CreateRoutePath(String routeName, List<String> cities) {
        this.routeName = routeName;
        this.cities = cities;
    }

    public CreateRoutePath() {
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}
