package com.xwaves.controller;

import com.xwaves.domain.User;
import com.xwaves.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*Login*/
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody User user) {
        if (userService.getByUsername(user.getUsername()) != null) {
            if (userService.getByUsername(user.getUsername()).getPassword().equals(user.getPassword())) {
                String jwtToken = Jwts.builder().setSubject(user.getEmail()).claim("roles", "user").setIssuedAt(new Date())
                        .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
                return new ResponseEntity<>(jwtToken, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect account");
        }
    }

    /*Registration*/
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.getByUsername(user.getUsername()) != null && userService.getByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Username and E-mail address already occupied");
        } else if (userService.getByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Username already occupied");
        } else if (userService.getByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("E-mail address already occupied");
        } else {
            user.setDate(new Date());
            userService.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("Registration successful");
        }
    }
}
