package com.services;

import com.model.Location;
import com.model.Route;
import com.payload.CreateRoutePath;
import com.payload.RoutePathPayload;
import com.payload.RoutesShowPayload;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class RoutePathServicesImpl implements RoutePathServices{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CreateRoutePath createRoutePath(CreateRoutePath createRoutePath) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Location> allLocations = session.createQuery("from Location", Location.class).list();
        HashMap<String, Integer> locMap = new HashMap<String, Integer>();
        HashMap<Integer, String> locMapIS = new HashMap<>();
        for (Location location : allLocations) {
            locMap.put(location.getLocationName(), location.getLocationId());
            locMapIS.put(location.getLocationId(), location.getLocationName());
        }
        List<String> allPaths = createRoutePath.getCities();
        if(allPaths.size() == 0) return null;
        String sql = "select * from route where route_name = '"+createRoutePath.getRouteName()+"'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Route.class);
        Route route = (Route)query.uniqueResult();
        System.out.println(route);
        int routeId = route.getRouteId();
        String rpsql = "SELECT r.route_id, GROUP_CONCAT(rp.location_id ORDER BY rp.seq_no ASC) AS sorted_location_ids " +
                "FROM route r " +
                "JOIN route_path rp ON r.route_id = rp.route_id " +
                "Where r.route_id = "+routeId+" "+
                "GROUP BY r.route_id ";
        SQLQuery rpquery = session.createSQLQuery(rpsql);
        List<Object[]> resultList = rpquery.getResultList();

        int seq = 1, check = 0;
        if(resultList.size() > 0) {
            String routeP = resultList.get(0)[1].toString();
            System.out.print("roteP : " + routeP);
            String[] routeArray = routeP.split(",");
            System.out.print("routeP arr : " + routeArray);
            if (routeArray.length > 0)
                seq = routeArray.length + 1;
        }
        List<RoutePathPayload> result = new ArrayList<>();
        for(String routeName : allPaths) {
            String  isql = "insert into route_path (route_id, location_id, seq_no) values( "+routeId + ", "+locMap.get(routeName)+", "+seq+")";
            SQLQuery query1 = session.createSQLQuery(isql);
            if(query1.executeUpdate() > 0){
                check++;
            }
            seq++;
        }
        transaction.commit();
        session.close();
        if(check != allPaths.size())  return null;
        return createRoutePath;
    }

    @Override
    public List<String> allRoutesNames() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<String> allRoutesNames = session.createQuery("select r.routeName from Route r", String.class).list();
        System.out.println(allRoutesNames);
        transaction.commit();
        session.close();
        return allRoutesNames;
    }

    @Override
    public List<RoutesShowPayload> showAllRoutes() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<RoutesShowPayload> routesShowPayloads = new ArrayList<>();
        List<Location> allLocations = session.createQuery("from Location", Location.class).list();
        HashMap<String, Integer> locMap = new HashMap<String, Integer>();
        HashMap<Integer, String> locMapIS = new HashMap<>();
        for (Location location : allLocations) {
            locMap.put(location.getLocationName(), location.getLocationId());
            locMapIS.put(location.getLocationId(), location.getLocationName());
        }
        String rpsql = "SELECT r.route_name, GROUP_CONCAT(rp.location_id ORDER BY rp.seq_no ASC) AS sorted_location_ids " +
                "FROM route r " +
                "JOIN route_path rp ON r.route_id = rp.route_id " +
                "GROUP BY r.route_id ";
        SQLQuery rpquery = session.createSQLQuery(rpsql);
        List<Object[]> rpList = rpquery.list();
        for (Object[] row : rpList) {
            RoutesShowPayload routesShowPayload = new RoutesShowPayload();
            String routeName = row[0].toString();
            routesShowPayload.setRouteName(routeName);
            String routeP = row[1].toString();
            String[] routeArray = routeP.split(",");
            String start = locMapIS.get(Integer.parseInt(routeArray[0]));
            String end = locMapIS.get(Integer.parseInt(routeArray[routeArray.length-1]));
            routesShowPayload.setStartLocation(start);
            routesShowPayload.setEndLocation(end);
            routesShowPayload.setNoOfStations(routeArray.length);
            routesShowPayload.setNoOfBuses(1);
            routesShowPayloads.add(routesShowPayload);
        }
        Collections.reverse(routesShowPayloads);
        return routesShowPayloads;
    }

    @Override
    public List<String> showRoute(String routeName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Location> allLocations = session.createQuery("from Location", Location.class).list();
        HashMap<String, Integer> locMap = new HashMap<String, Integer>();
        HashMap<Integer, String> locMapIS = new HashMap<>();
        for (Location location : allLocations) {
            locMap.put(location.getLocationName(), location.getLocationId());
            locMapIS.put(location.getLocationId(), location.getLocationName());
        }
        String rpsql = "SELECT r.route_name, GROUP_CONCAT(rp.location_id ORDER BY rp.seq_no ASC) AS sorted_location_ids " +
                "FROM route r " +
                "JOIN route_path rp ON r.route_id = rp.route_id " +
                "WHERE r.route_name = '"+routeName+"' "+
                "GROUP BY r.route_id ";
        SQLQuery rpquery = session.createSQLQuery(rpsql);
        List<String> allLocations1 = new ArrayList<>();
        List<Object[]> rpList = rpquery.list();
        for (Object[] row : rpList) {
            String routeP = row[1].toString();
            String[] routeArray = routeP.split(",");
            for (int i = 0; i < routeArray.length; i++) {
                allLocations1.add(locMapIS.get(Integer.parseInt(routeArray[i])));
            }
        }
        return allLocations1;
    }


}
