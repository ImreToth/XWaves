package com.xwaves.controllers;

import com.xwaves.Model.User;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("/test")
    public String index() {
        User u = new User();
        u.setEmail("test@test.com");
        u.setPassword("test1234");
        u.setUsername("test");
        u.setRegtime(LocalDateTime.now().toString());
        return u.toString();
    }
}