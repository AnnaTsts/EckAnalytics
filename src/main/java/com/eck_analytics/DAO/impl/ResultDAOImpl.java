package com.eck_analytics.DAO.impl;

import com.eck_analytics.DAO.HibernateSessionFactoryUtil;
import com.eck_analytics.DAO.ResultDAO;
import com.eck_analytics.Model.Result;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResultDAOImpl implements ResultDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Result findById(int id) {
        return sessionFactory.getCurrentSession().get(Result.class, id);
    }

    @Override
    public Integer save(Result result) {
        int id = 0;
        Session session = sessionFactory.getCurrentSession();
        result.setResultString(result.getResultString().replaceAll("\u0000", ""));
        try {
            session.save(result);
            id = result.getId();
        } catch (HibernateException he) {
        }

        return id;
    }

    @Override
    public void update(Result result) {
        result.setResultString(result.getResultString().replaceAll("\u0000", ""));
        Session session = sessionFactory.getCurrentSession();
        session.update(result);
    }

    @Override
    public void delete(Result result) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(result);
    }

    @Override
    public List<Result> findAll() {
        List<Result> results = (List<Result>)  sessionFactory.getCurrentSession().createQuery("From Result ").list();
        return results;
    }
}
