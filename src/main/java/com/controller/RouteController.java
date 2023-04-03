package com.controller;

import com.model.Bus;
import com.model.Route;
import com.payload.BusRoutesPayload;
import com.payload.InterRidesPayload;
import com.services.RouteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {
    @Autowired
    private RouteServices routeServices;

    @PostMapping("/getbusinter")
    List<List<BusRoutesPayload>> allBusFromStartToEndIntermediaryPoints(@RequestBody InterRidesPayload interRidesPayload){
        return routeServices.allBusFromStartToEndIntermediaryPoints(interRidesPayload);
    }

    @PostMapping("/chooseroute/{routeName}")
    List<String> chooseRoute(@PathVariable("routeName") String routeName){
        return routeServices.chooseRoute(routeName);
    }

    @PostMapping("/createroute")
    Route createRotue(@RequestBody Route route) {
        return routeServices.createRoute(route);
    }

    @GetMapping("/getroute/{routename}")
    List<String> allRoutes(@PathVariable("routename") String routeName){
        return routeServices.getAllRoutesWithName(routeName);
    }

}
