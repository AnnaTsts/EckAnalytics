package com.eck_analytics.DAO;

import com.eck_analytics.Model.Example;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExampleDAO {
    Example findById(int id);

    void save(Example example);

    void update(Example example);

    void delete(Example example);

    List<Example> findAll();
}
