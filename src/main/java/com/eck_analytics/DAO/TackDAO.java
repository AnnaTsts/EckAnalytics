package com.eck_analytics.DAO;

import com.eck_analytics.Model.Tact;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TackDAO {

    //TODO rewrite with generic com.diploma.DAO
    Tact findById(int id);

    Integer save(Tact tact);

    void update(Tact tact);

    void delete(Tact tact);

    List<Tact> findAll();
}
