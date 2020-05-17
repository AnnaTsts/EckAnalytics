package com.eck_analytics.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Data
@Entity
@Table(name = "user_data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "is_mail_verified")
    private boolean isMailVerified;

    public User(int authorityId, String username, String password,  String email,boolean isActive) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.isMailVerified = isActive;
    }

    public User() {

    }
}
