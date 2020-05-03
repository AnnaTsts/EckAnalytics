package com.eck_analytics.DAO;

import com.eck_analytics.Model.Result;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ResultDAO {
    public Result findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Result.class, id);
    }

    public Integer save(Result result) {
        int id = 0;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(result);
            id = result.getId();
            transaction.commit();
        } catch (HibernateException he) {
        }

        session.close();
        return id;
    }

    public void update(Result result) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(result);
        transaction.commit();
        session.close();
    }

    public void delete(Result result) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(result);
        transaction.commit();
        session.close();
    }

    public List<Result> findAll() {
        List<Result> results = (List<Result>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From result").list();
        return results;
    }
}
