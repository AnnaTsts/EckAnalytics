package com.eck_analytics.DAO.impl;

import com.eck_analytics.DAO.PersonDAO;
import com.eck_analytics.Model.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Person findById(int id) {
        return sessionFactory.getCurrentSession().get(Person.class, id);
    }

    @Override
    public void save(Person person) {
        int id = 0;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(person);
            id = person.getId();
        } catch (HibernateException he) {
        }
    }

    @Override
    public void update(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.update(person);
    }

    @Override
    public void delete(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(person);
    }

    @Override
    public List<Person> findAll() {
        List<Person> results = (List<Person>)  sessionFactory.getCurrentSession().createQuery("From result").list();
        return results;
    }


}
