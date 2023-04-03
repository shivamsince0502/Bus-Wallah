package com.services;
import com.model.Location;
import com.payload.AddLocationPayload;
import com.payload.NextLocationPayload;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LocationServicesImpl implements LocationServices {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<String> allLocationOfStr(String locName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT location_name FROM location WHERE location_name LIKE '"+locName+"%'";
        SQLQuery query = session.createSQLQuery(sql);
        List<String> cities = query.list();
        transaction.commit();
        session.close();
        return cities;
    }

    @Override
    public String createLocation(String locationName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(locationName);
        transaction.commit();
        session.close();
        return locationName;
    }

    @Override
    public List<String> nextLocation(NextLocationPayload nextLocationPayload) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String locationName = nextLocationPayload.getCurrLocation();
        List<String> prevLocations = nextLocationPayload.getPrevLocations();
        List<Location> allLocations = session.createQuery("from Location", Location.class).list();
        HashMap<String, Integer> locMap = new HashMap<String, Integer>();
        HashMap<Integer, String> locMapIS = new HashMap<>();
        for (Location location : allLocations) {
            locMap.put(location.getLocationName(), location.getLocationId());
            locMapIS.put(location.getLocationId(), location.getLocationName());
        }
        String sql = "select * from location where location_name = '"+locationName+"'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Location.class);
        Location location = (Location)query.uniqueResult();
        String psql = "SELECT DISTINCT `from_id`, `to_id` " +
                "FROM edge " +
                "WHERE `from_id` = "+location.getLocationId()+" OR `to_id` = "+location.getLocationId();
        SQLQuery query1 = session.createSQLQuery(psql);
        List<Object[]> resultList = query1.getResultList();
        List<String> reqResult = new ArrayList<>();
        for(Object[] row : resultList) {
            int from = Integer.parseInt(row[0].toString());
            int to = Integer.parseInt(row[1].toString());
            if(from != location.getLocationId() && !(prevLocations.contains(locMapIS.get(from)))) reqResult.add(locMapIS.get(from));
            if(to != location.getLocationId() && !(prevLocations.contains(locMapIS.get(to)))) reqResult.add(locMapIS.get(to));
        }
        return reqResult;
    }

    @Override
    public List<String> addLocations(AddLocationPayload addLocationPayload) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println(addLocationPayload.getLocationNames());
        String sql = "insert into location(location_name) values";
        for(int i = 0; i < addLocationPayload.getLocationNames().size(); i++) {
            String loc = addLocationPayload.getLocationNames().get(i);
            if(i != addLocationPayload.getLocationNames().size()-1) sql += "('"+loc+"'),";
            else sql += "('"+loc+"')";
        }
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return addLocationPayload.getLocationNames();
    }
}
