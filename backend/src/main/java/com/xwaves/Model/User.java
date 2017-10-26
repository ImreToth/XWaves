package com.xwaves.Model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class User {
    private String username;
    private String password;
    private String email;
    private String regtime;

    public User(){
    
    }
    
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date Date = new Date();
        this.regtime=sdf.format(Date);
    }
    
    public User(String username, String password, String email,String regtime) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.regtime=regtime;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRegtime() {
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date Date = new Date();
        regtime=sdf.format(Date);
        return regtime;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegtime(String regtime) {
        this.regtime = regtime;
    }
    
    @Override
    public String toString() {
        return "{\"User\":{\"username\": \"" + username 
                + "\", \"email\": \"" + email 
                + "\", \"password\": \"" + password 
                + "\", \"date\": \"" + regtime + "\"}}";
    }
}