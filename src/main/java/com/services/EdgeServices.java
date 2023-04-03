package com.services;

import com.model.Edge;
import com.payload.AddEdgePayload;

public interface EdgeServices {
    Edge addEdge(AddEdgePayload edge);
}
