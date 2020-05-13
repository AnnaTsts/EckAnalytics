package com.eck_analytics.DAO;

import com.eck_analytics.Model.Result;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultDAO {
    Result findById(int id);

    Integer save(Result result);

    void update(Result result);

    void delete(Result result);

    List<Result> findAll();
}
