package com.eck_analytics.DAO.impl;

import com.eck_analytics.DAO.HibernateSessionFactoryUtil;
import com.eck_analytics.DAO.TackDAO;
import com.eck_analytics.Model.Tact;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TackDAOImpl implements TackDAO {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Tact findById(int id) {
        return sessionFactory.getCurrentSession().get(Tact.class, id);
    }

    @Override
    public Integer save(Tact tact) {
        Session session = sessionFactory.getCurrentSession();
        session.save(tact);
        return tact.getId();
    }

    @Override
    public void update(Tact tact) {
        Session session = sessionFactory.getCurrentSession();
        session.update(tact);
    }

    @Override
    public void delete(Tact tact) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(tact);
    }

    @Override
    public List<Tact> findAll() {
        List<Tact> results = (List<Tact>) sessionFactory.getCurrentSession().createQuery("From Tact ").list();
        return results;
    }
}
