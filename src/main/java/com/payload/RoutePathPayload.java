package com.payload;

public class RoutePathPayload {
    private String routeName;
    private String end;
    private int dist;

    public RoutePathPayload(String routeName, String end, int dist) {
        this.routeName = routeName;
        this.end = end;
        this.dist = dist;
    }

    public RoutePathPayload() {
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
}
