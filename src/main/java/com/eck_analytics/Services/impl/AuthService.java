package com.eck_analytics.Services.impl;


import com.eck_analytics.DAO.UserDAO;
import com.eck_analytics.Model.User;
import com.eck_analytics.Utils.Constants;
import com.eck_analytics.dto.request.LoginRequest;
import com.eck_analytics.dto.request.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eck_analytics.security.JwtTokenProvider;

@Slf4j
@Service
@Transactional
public class AuthService {

    @Autowired
    private UserDAO userDao;


    @Transactional
    public ResponseEntity registerUser(SignUpRequest request, PasswordEncoder encoder) {

        if (userDao.getDetailedUserByUsernameOrEmail(request.getUsername())!=null) {
            return Constants.ResponseEntities.BAD_REQ_USERNAME_TAKEN;
        }
        if (userDao.getDetailedUserByUsernameOrEmail(request.getEmail())!=null) {
            return Constants.ResponseEntities.BAD_REQ_EMAIL_TAKEN;
        }

        //change authority
        User user = new User(1, request.getUsername(),
                encoder.encode(request.getPassword()), request.getEmail(),false);

        userDao.save(user);

        return Constants.ResponseEntities.USER_REGISTERED_SUCCESSFULLY;
    }

    public String authenticateUser(LoginRequest request, AuthenticationManager authManager, JwtTokenProvider tokenProvider) {
        log.debug("Request: {}|{}", request.getUsernameOrEmail(), request.getPassword());
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsernameOrEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.generateToken(authentication);
    }
}
