package com.eck_analytics.DAO;

import com.eck_analytics.Model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO {
    User findById(int id);

    User getDetailedUserByUsernameOrEmail(String cred);

    Integer save(User user);

    void update(User user);

    void delete(User user);

    List<User> findAll();
}
