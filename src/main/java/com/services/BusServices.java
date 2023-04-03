package com.services;

import com.model.Bus;
import com.payload.AddBusPayload;

import java.util.List;

public interface BusServices {
    Bus addBus(AddBusPayload addBusPayload);
    List<String> allBusNamesWithStr(String str);
    List<String> allBustNumberWithStr(String str);
    List<Bus> allbusses();
}
