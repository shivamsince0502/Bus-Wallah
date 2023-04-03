package com.controller;

import com.model.Location;
import com.payload.AddLocationPayload;
import com.payload.NextLocationPayload;
import com.services.LocationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationServices locationServices;

    @GetMapping("getLocation/{loc}")
    List<String> allLocations(@PathVariable("loc") String locName){
        return locationServices.allLocationOfStr(locName);
    }

    @PostMapping("/createlocation/{locname}")
    private String createLocation(@PathVariable("locname") String locName){
        return locationServices.createLocation(locName);
    }

    @PostMapping("/nextlocation")
    private List<String> nextLocation(@RequestBody NextLocationPayload nextLocationPayload){
        return locationServices.nextLocation(nextLocationPayload);
    }
    @PostMapping("/addlocations")
    private List<String> addLocations(@RequestBody AddLocationPayload addLocationPayload){
        return locationServices.addLocations(addLocationPayload);
    }

}
