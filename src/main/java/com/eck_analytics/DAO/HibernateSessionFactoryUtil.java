package com.eck_analytics.DAO;


import com.eck_analytics.Model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Anomaly.class);
                configuration.addAnnotatedClass(Example.class);
                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(Result.class);
                configuration.addAnnotatedClass(Tact.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Error with sessionFactory!" + e);
            }
        }
        return sessionFactory;
    }
}
