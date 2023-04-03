package com.services;


import com.model.Location;
import com.payload.AddLocationPayload;
import com.payload.NextLocationPayload;

import java.util.List;

public interface LocationServices {
    List<String> allLocationOfStr(String str);
    String createLocation(String locationName);

    List<String> nextLocation(NextLocationPayload nextLocationPayload);
    List<String> addLocations(AddLocationPayload addLocationPayload);
}
