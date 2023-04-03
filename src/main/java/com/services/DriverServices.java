package com.services;

import com.model.Driver;
import com.payload.AddDriverPayload;

public interface DriverServices {
    Driver addDriver(AddDriverPayload addDriverPayload);
}
