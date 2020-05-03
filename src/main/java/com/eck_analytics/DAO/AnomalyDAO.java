package com.eck_analytics.DAO;


import com.eck_analytics.Model.Anomaly;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AnomalyDAO {
    public Anomaly findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Anomaly.class, id);
    }

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

    public void update(Anomaly anomaly) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(anomaly);
        transaction.commit();
        session.close();
    }

    public void delete(Anomaly anomaly) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(anomaly);
        transaction.commit();
        session.close();
    }

    public List<Anomaly> findAll() {
        List<Anomaly> anomalies = (List<Anomaly>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From anomaly").list();
        return anomalies;
    }
}
