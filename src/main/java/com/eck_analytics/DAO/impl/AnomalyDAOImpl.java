package com.eck_analytics.DAO.impl;


import com.eck_analytics.DAO.AnomalyDAO;
import com.eck_analytics.DAO.HibernateSessionFactoryUtil;
import com.eck_analytics.Model.Anomaly;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnomalyDAOImpl implements AnomalyDAO {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Anomaly findById(int id) {
        return sessionFactory.getCurrentSession().get(Anomaly.class, id);
    }

    @Override
    public Integer save(Anomaly anomaly) {
        int id = 0;
        Session session = sessionFactory.getCurrentSession();
        try {
            anomaly.setAnomalyString(anomaly.getAnomalyString().replaceAll("\u0000", ""));
            session.save(anomaly);
            id = anomaly.getId();
        } catch (HibernateException he) {
        }
        return id;
    }

    @Override
    public void update(Anomaly anomaly) {
        anomaly.setAnomalyString(anomaly.getAnomalyString().replaceAll("\u0000", ""));
        Session session =sessionFactory.getCurrentSession();
        session.update(anomaly);
    }

    @Override
    public void delete(Anomaly anomaly) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(anomaly);
    }

    @Override
    public List<Anomaly> findAll() {
        return (List<Anomaly>) sessionFactory.getCurrentSession().createQuery("From Anomaly ").list();
    }
}
