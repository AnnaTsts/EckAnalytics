package com.eck_analytics.DAO;


import com.eck_analytics.Model.Example;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ExampleDAO {
    public Example findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Example.class, id);
    }

    public void save(Example example) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(example);
        transaction.commit();
        session.close();
    }

    public void update(Example example) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(example);
        transaction.commit();
        session.close();
    }

    public void delete(Example example) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(example);
        transaction.commit();
        session.close();
    }


    public List<Example> findAll() {
        List<Example> examples = (List<Example>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From example").list();
        return examples;
    }
}
