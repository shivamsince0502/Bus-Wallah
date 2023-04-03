package com.payload;

import java.util.List;

public class InterRidesPayload {
    private String start;
    private String end;
    private List<String> intercities;

    public InterRidesPayload() {
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<String> getIntercities() {
        return intercities;
    }

    public void setIntercities(List<String> intercities) {
        this.intercities = intercities;
    }

    public InterRidesPayload(String start, String end, List<String> intercities) {
        this.start = start;
        this.end = end;
        this.intercities = intercities;
    }
}
