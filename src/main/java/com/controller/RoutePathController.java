package com.controller;

import com.payload.CreateRoutePath;
import com.payload.RoutesShowPayload;
import com.services.RoutePathServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/routepath")
public class RoutePathController {
    @Autowired
    private RoutePathServices routePathServices;

    @GetMapping("/allroutes")
    List<String> allroutesnames(){
        return routePathServices.allRoutesNames();
    }

    @PostMapping("/createroutepath")
    CreateRoutePath createRoutePath(@RequestBody CreateRoutePath createRoutePath) {
        return routePathServices.createRoutePath(createRoutePath);
    }

    @GetMapping("/allroutesdata")
    List<RoutesShowPayload> allroutes(){
        return routePathServices.showAllRoutes();
    }

    @GetMapping("/wholepath/{routename}")
    List<String> wholePath(@PathVariable("routename")String routeName){
        return routePathServices.showRoute(routeName);
    }

}
