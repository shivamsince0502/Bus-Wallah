package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "edge")
public class Edge {
    @Id
    @Column(name = "edge_id")
    private int edgeId;

    @Column(name = "from_id")
    private int fromId;

    @Column(name = "to_id")
    private int toId;

    @Column(name = "distance")
    private int distance;
    public Edge(){}


    @Override
    public String toString() {
        return "Edge{" +
                "edgeId=" + edgeId +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", distance=" + distance +
                '}';
    }

    public int getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(int edgeId) {
        this.edgeId = edgeId;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Edge(int edgeId, int fromId, int toId, int distance) {
        this.edgeId = edgeId;
        this.fromId = fromId;
        this.toId = toId;
        this.distance = distance;
    }
}
