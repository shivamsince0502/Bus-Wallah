package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "route_path")
public class RoutePath {
    @Id
    @Column(name = "path_id")
    private int pathId;

    @Column(name = "route_id")
    private int routeId;

    @Column(name = "location_id")
    private int locationId;

    @Column(name = "seq_no")
    private int seqNo;

    public RoutePath(){}
    public int getPathId() {
        return pathId;
    }

    public void setPathId(int pathId) {
        this.pathId = pathId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public RoutePath(int pathId, int routeId, int locationId, int seqNo) {
        this.pathId = pathId;
        this.routeId = routeId;
        this.locationId = locationId;
        this.seqNo = seqNo;
    }
}
