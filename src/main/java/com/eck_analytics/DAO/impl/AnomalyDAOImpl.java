package com.eck_analytics.DAO.impl;


import com.eck_analytics.DAO.AnomalyDAO;
import com.eck_analytics.DAO.HibernateSessionFactoryUtil;
import com.eck_analytics.Model.Anomaly;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnomalyDAOImpl implements AnomalyDAO {

    @Override
    public Anomaly findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Anomaly.class, id);
    }

    @Override
    public Integer save(Anomaly anomaly) {
        int id = 0;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(anomaly);
            id = anomaly.getId();
            transaction.commit();
        } catch (HibernateException he) {
        }

        session.close();
        return id;
    }

    @Override
    public void update(Anomaly anomaly) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(anomaly);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Anomaly anomaly) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(anomaly);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Anomaly> findAll() {
        return (List<Anomaly>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From anomaly").list();
    }
}
