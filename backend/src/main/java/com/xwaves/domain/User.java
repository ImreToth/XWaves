package com.xwaves.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String username;
    private String email;
    private String password;
    private Date datetime;

    public User() {
    }

    public User(long id, String username, String email, String password, Date datetime) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.datetime = datetime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return datetime;
    }

    public void setDate(Date datetime) {
        this.datetime = datetime;
    }
    
}