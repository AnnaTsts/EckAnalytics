package com.eck_analytics.Services;

import com.eck_analytics.Model.Person;
import com.eck_analytics.dto.response.UserSummary;
import org.springframework.stereotype.Service;

@Service
public interface PersonService {
    Person getById(int id);

    void savePerson(Person person);

    void deletePerson(Person person);

    void updatePerson(Person person);

    UserSummary getUserSummary(int id);
}
