package com.services;

import com.model.Bus;
import com.model.Route;
import com.payload.AddBusPayload;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServicesImpl implements BusServices{
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Bus addBus(AddBusPayload addBusPayload) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Bus bus = new Bus();
        String sql = "select * from route where route_name = '"+addBusPayload.getRouteName()+"'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Route.class);
        Route route = (Route)query.uniqueResult();
        int routeId = route.getRouteId();
        bus.setBusName(addBusPayload.getBusName());
        bus.setBusNumber(addBusPayload.getBusNumber());
        bus.setNoOfSeats(addBusPayload.getNoOfSeats());
        bus.setRouteId(routeId);
        bus.setActive(true);
        session.save(bus);
        transaction.commit();
        session.close();
        return bus;
    }

    @Override
    public List<String> allBusNamesWithStr(String str) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT bus_name FROM bus WHERE bus_name LIKE '"+str+"%'";
        SQLQuery query = session.createSQLQuery(sql);
        List<String> cities = query.list();
        transaction.commit();
        session.close();
        return cities;
    }

    @Override
    public List<String> allBustNumberWithStr(String str) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
            String sql = "SELECT bus_number FROM bus WHERE bus_number LIKE '"+str+"%'";
            SQLQuery query = session.createSQLQuery(sql);
            List<String> cities = query.list();
            transaction.commit();
            session.close();
            return cities;

    }

    @Override
    public List<Bus> allbusses() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Bus> allbuslist = session.createQuery("from Bus", Bus.class).list();
        transaction.commit();
        session.close();
        return allbuslist;
    }
}
