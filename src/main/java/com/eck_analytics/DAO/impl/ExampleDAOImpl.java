package com.eck_analytics.DAO.impl;


import com.eck_analytics.DAO.ExampleDAO;
import com.eck_analytics.DAO.HibernateSessionFactoryUtil;
import com.eck_analytics.Model.Example;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExampleDAOImpl implements ExampleDAO {

    @Override
    public Example findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Example.class, id);
    }

    @Override
    public void save(Example example) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(example);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Example example) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(example);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Example example) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(example);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Example> findAll() {
        return (List<Example>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From example").list();
    }
}
