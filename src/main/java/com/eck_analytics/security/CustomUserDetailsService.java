package com.eck_analytics.security;


import com.eck_analytics.DAO.UserDAO;
import com.eck_analytics.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        User user = userDAO.getDetailedUserByUsernameOrEmail(usernameOrEmail);


        if (user==null) {
            throw new UsernameNotFoundException("User not found with usernameOrEmail : " + usernameOrEmail);
        }

        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(int id) {
        User user = userDAO.findById(id);
        if (user==null) {
            throw new UsernameNotFoundException("User not found with id : " + id);
        }
        return UserPrincipal.create(user);
    }
}
