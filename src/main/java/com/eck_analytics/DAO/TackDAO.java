package com.eck_analytics.DAO;

import com.eck_analytics.Model.Tact;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class TackDAO {

    //TODO rewrite with generic com.diploma.DAO
    public Tact findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Tact.class, id);
    }

    public Integer save(Tact tact) {
        int id = 0;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(tact);
            id = tact.getId();
            transaction.commit();
        } catch (HibernateException he) {
        }

        session.close();
        return id;
    }

    public void update(Tact tact) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(tact);
        transaction.commit();
        session.close();
    }

    public void delete(Tact tact) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(tact);
        transaction.commit();
        session.close();
    }

    public List<Tact> findAll() {
        List<Tact> results = (List<Tact>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From tack").list();
        return results;
    }
}
