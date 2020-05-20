package com.eck_analytics.DAO;

import com.eck_analytics.Model.Person;

import java.util.List;

public interface PersonDAO {
    Person findById(int id);

    void save(Person example);

    void update(Person example);

    void delete(Person example);

    List<Person> findAll();
}
