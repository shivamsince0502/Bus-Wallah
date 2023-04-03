package com.controller;

import com.model.Driver;
import com.payload.AddDriverPayload;
import com.services.DriverServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    private DriverServices driverServices;

    @PostMapping("/adddriver")
    Driver addDriver(@RequestBody AddDriverPayload driver){
        return driverServices.addDriver(driver);
    }

}
