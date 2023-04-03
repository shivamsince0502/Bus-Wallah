package com.services;

import com.model.Edge;
import com.model.Location;
import com.payload.AddEdgePayload;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EdgeServicesImpl implements EdgeServices{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Edge addEdge(AddEdgePayload edge) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * FROM location WHERE location_name = :locationName";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Location.class);
        query.setParameter("locationName", edge.getFrom());
        Location loc = (Location) query.uniqueResult();

        String sql1 = "SELECT * FROM location WHERE location_name = :locationName";
        SQLQuery query1 = session.createSQLQuery(sql1);
        query1.addEntity(Location.class);
        query1.setParameter("locationName", edge.getTo());
        Location loc1 = (Location) query1.uniqueResult();
        System.out.println(loc +" : "+ loc1);
        Edge edge1 = new Edge();
        edge1.setFromId(loc1.getLocationId());
        edge1.setToId(loc.getLocationId());
        edge1.setDistance(edge.getDistance());
        System.out.println(edge1);
        session.saveOrUpdate(edge1);
        transaction.commit();
        session.close();
        return edge1;
    }
}
