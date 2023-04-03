package com.services;

import com.model.Bus;
import com.model.Route;
import com.payload.BusRoutesPayload;
import com.payload.InterRidesPayload;

import java.util.*;

public interface RouteServices {
    List<List<BusRoutesPayload>>  allBusFromStartToEnd(String start, String end);

    List<List<BusRoutesPayload>> allBusFromStartToEndIntermediaryPoints(InterRidesPayload interRidesPayload);

    List<String> chooseRoute(String routeName);

    Route createRoute(Route route);
    List<String> getAllRoutesWithName(String routeName);
}
