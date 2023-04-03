package com.services;

import com.model.Bus;
import com.model.Driver;
import com.payload.AddDriverPayload;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServicesImpl implements DriverServices{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Driver addDriver(AddDriverPayload addDriverPayload) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Driver driver = new Driver();
        driver.setDriverEmail(addDriverPayload.getDriverEmail());
        driver.setDriverName(addDriverPayload.getDriverName());
        driver.setDriverMob(addDriverPayload.getDriverNumber());
        String sql = "select * from bus where bus_name = '"+addDriverPayload.getBusName()+"'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Bus.class);
        Bus bus = (Bus)query.uniqueResult();
        driver.setBusId(bus.getBusId());
        session.save(driver);
        transaction.commit();
        session.close();
        return driver;
    }
}
