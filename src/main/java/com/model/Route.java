package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class Route {
    @Id
    @Column(name = "route_id")
    private int routeId;

    @Column(name = "route_name")
    private String routeName;

    @Column(name = "is_active")
    private Boolean isActive;

    public Route(int routeId, String routeName, Boolean isActive) {
        this.routeId = routeId;
        this.routeName = routeName;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", routeName='" + routeName + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    public Route(){}
    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
