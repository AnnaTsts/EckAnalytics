package com.eck_analytics.DAO.impl;

import com.eck_analytics.DAO.HibernateSessionFactoryUtil;
import com.eck_analytics.DAO.TackDAO;
import com.eck_analytics.Model.Tact;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TackDAOImpl implements TackDAO {

    //TODO rewrite with generic com.diploma.DAO
    @Override
    public Tact findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Tact.class, id);
    }

    @Override
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

    @Override
    public void update(Tact tact) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(tact);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Tact tact) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(tact);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Tact> findAll() {
        List<Tact> results = (List<Tact>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From tack").list();
        return results;
    }
}
