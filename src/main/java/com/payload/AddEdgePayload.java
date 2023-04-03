package com.payload;

public class AddEdgePayload {
    private String from;
    private String to;
    private  int distance;

    public AddEdgePayload(String from, String to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public AddEdgePayload() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
