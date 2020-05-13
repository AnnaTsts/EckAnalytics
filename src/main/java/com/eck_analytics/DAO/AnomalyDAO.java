package com.eck_analytics.DAO;


import com.eck_analytics.Model.Anomaly;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnomalyDAO {
    Anomaly findById(int id);

    Integer save(Anomaly anomaly);

    void update(Anomaly anomaly);

    void delete(Anomaly anomaly);

    List<Anomaly> findAll();
}
