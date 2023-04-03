package com.controller;

import com.model.Edge;
import com.payload.AddEdgePayload;
import com.services.EdgeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/edge")
public class EdgeController {
    @Autowired
    private EdgeServices edgeServices;

    @PostMapping("/addedge")
    private Edge addEdge(@RequestBody AddEdgePayload edge){
        return edgeServices.addEdge(edge);
    }
}
