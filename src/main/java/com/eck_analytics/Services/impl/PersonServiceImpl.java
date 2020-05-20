package com.eck_analytics.Services.impl;

import com.eck_analytics.DAO.PersonDAO;
import com.eck_analytics.DAO.UserDAO;
import com.eck_analytics.Model.Person;
import com.eck_analytics.Model.User;
import com.eck_analytics.Services.PersonService;
import com.eck_analytics.dto.response.UserSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    private PersonDAO personDAO;

    private UserDAO userDAO;

    @Autowired
    public PersonServiceImpl(PersonDAO personDAO, UserDAO userDAO) {
        this.personDAO = personDAO;
        this.userDAO = userDAO;
    }


    @Override
    public Person getById(int id) {
        return personDAO.findById(id);
    }

    @Override
    public void savePerson(Person person) {
        personDAO.save(person);
    }

    @Override
    public void deletePerson(Person person) {
        personDAO.delete(person);
    }

    @Override
    public void updatePerson(Person person) {
        personDAO.update(person);
    }

    @Override
    public UserSummary getUserSummary(int id) {
        UserSummary userSummary = new UserSummary();
        User user = userDAO.findById(id);
        Person person = personDAO.findById(id);
        userSummary.setUsername(user.getUsername());
        userSummary.setEmail(user.getEmail());

        if (person != null) {
            userSummary.setAge(person.getAge());
            userSummary.setHeight(person.getHeight());
            userSummary.setWeight(person.getWeight());
            userSummary.setSex(person.getSex());
        }

        return userSummary;
    }
}
