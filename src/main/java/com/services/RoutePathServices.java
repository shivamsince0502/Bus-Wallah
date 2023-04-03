package com.services;

import com.payload.CreateRoutePath;
import com.payload.RoutePathPayload;
import com.payload.RoutesShowPayload;

import java.util.List;

public interface RoutePathServices {
    CreateRoutePath createRoutePath(CreateRoutePath createRoutePath);

    List<String> allRoutesNames();
    List<RoutesShowPayload> showAllRoutes();
    List<String> showRoute(String routeName);
}
