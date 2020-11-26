package com.dgarg20.demo.repository;

import com.dgarg20.demo.entities.Alerts;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

/**
 * Created by Deepanshu Garg on 26/11/20.
 */
public class AlertsDao extends AbstractDAO<Alerts> {

    @Inject
    public AlertsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void save(Alerts alerts) {
        persist(alerts);
    }

    public Optional<Alerts> changeAlertStatus() {
        Criteria criteria = this.currentSession().createCriteria(Alerts.class);
        criteria.add(Restrictions.eq("status", "Pending"));
        List<Alerts> alertsList  = criteria.list();
        if(alertsList.isEmpty()) {
            Optional.empty();
        }
        return Optional.of(alertsList.get(0));
    }



    public void changeAlertStatus(Alerts alerts, String status) {
        alerts.setStatus(status);
        String sql = "select * from alerts where status = 'Pending' LIMIT 1 FOR UPDATE";
        this.currentSession().merge(alerts);
    }
}
