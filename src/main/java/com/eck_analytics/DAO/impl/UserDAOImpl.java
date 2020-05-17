package com.eck_analytics.DAO.impl;

import com.eck_analytics.DAO.HibernateSessionFactoryUtil;
import com.eck_analytics.DAO.UserDAO;
import com.eck_analytics.Model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {


    @Autowired
    private SessionFactory sessionFactory;

    public User findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public User getDetailedUserByUsernameOrEmail(String cred) {
        List<User> user = sessionFactory.getCurrentSession().
                createQuery("From User WHERE email = :email", User.class).setParameter("email", cred).list();
        if (user.size()==0)
            user= sessionFactory.getCurrentSession().
                createQuery("From User WHERE username = :username", User.class).setParameter("username", cred).list();
        if(user.size()==0)
            return null;
        return user.get(0);
    }

    @Override
    public Integer save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        return user.getId();
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public List<User> findAll() {
        List<User> results = (List<User>) sessionFactory.getCurrentSession().createQuery("From user_data").list();
        return results;
    }


}
