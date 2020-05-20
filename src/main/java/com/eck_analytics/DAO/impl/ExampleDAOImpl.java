package com.eck_analytics.DAO.impl;


import com.eck_analytics.DAO.ExampleDAO;
import com.eck_analytics.DAO.HibernateSessionFactoryUtil;
import com.eck_analytics.Model.Example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExampleDAOImpl implements ExampleDAO {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Example findById(int id) {
        return sessionFactory.getCurrentSession().get(Example.class, id);
    }

    @Override
    public void save(Example example) {
        Session session = sessionFactory.getCurrentSession();
        session.save(example);
    }

    @Override
    public void update(Example example) {
        Session session = sessionFactory.getCurrentSession();
        session.update(example);
    }

    @Override
    public void delete(Example example) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(example);
    }

    @Override
    public List<Example> findAll() {
        return (List<Example>) sessionFactory.getCurrentSession().createQuery("From Example ").list();
    }
}
