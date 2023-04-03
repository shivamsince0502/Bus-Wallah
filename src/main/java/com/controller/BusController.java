package com.controller;

import com.model.Bus;
import com.payload.AddBusPayload;
import com.services.BusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {
    @Autowired
    private BusServices busServices;

    @PostMapping("/addbus")
    private Bus addBus(@RequestBody AddBusPayload addBusPayload){
        return busServices.addBus(addBusPayload);
    }

    @GetMapping("/allbuwithname/{busname}")
    private List<String> allBusWithName(@PathVariable("busname")String busName){
        return busServices.allBusNamesWithStr(busName);
    }
    @GetMapping("/allbuwithnumber/{busnumber}")
    private List<String> allBusWithNumber(@PathVariable("busnumber")String busNumber){
        return busServices.allBustNumberWithStr(busNumber);
    }
    @GetMapping("/allbus")
    private List<Bus> allBus(){
        return busServices.allbusses();
    }
}
