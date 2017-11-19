package com.xwaves.controller;

import com.xwaves.domain.User;
import com.xwaves.service.UserService;
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
public class HomeController {

    private UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody User user) {
        if (userService.getByUsername(user.getUsername()) != null) {
            if (userService.getByUsername(user.getUsername()).getPassword().equals(user.getPassword())) {
                System.out.println("Sikeres belépés!");
                return new ResponseEntity<>(userService.getByUsername(user.getUsername()), HttpStatus.OK);
            } else {
                System.out.println("Helytelen jelszó!");
                return new ResponseEntity<>("Helytelen jelszó!", HttpStatus.OK);
            }
        } else {
            System.out.println("Nincs ilyen felhasználó!");
            return new ResponseEntity<>("Nincs ilyen felhasználó!", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.getByUsername(user.getUsername()) != null && userService.getByEmail(user.getEmail()) != null) {
            System.out.println("Felhasználó és email foglalt!");
            return new ResponseEntity<>("Felhasználónév és email cím már foglalt!", HttpStatus.OK);
        } else if (userService.getByUsername(user.getUsername()) != null) {
            System.out.println("Felhasználó foglalt!");
            return new ResponseEntity<>("Felhasználónév már foglalt!", HttpStatus.OK);
        } else if (userService.getByEmail(user.getEmail()) != null) {
            System.out.println("Email foglalt!");
            return new ResponseEntity<>("Email cím már foglalt!", HttpStatus.OK);
        } else {
            user.setDate(new Date());
            userService.save(user);
            System.out.println("Sikeres regisztráció!");
            return new ResponseEntity<>("Sikeres regisztráció!", HttpStatus.OK); 
        }
    }

    @RequestMapping("/monsters")
    public ResponseEntity<?> monsters() {
        return new ResponseEntity<>("{\"monsters\":" + "}", HttpStatus.OK);
    }

    @RequestMapping("/items")
    public ResponseEntity<?> items() {
        return new ResponseEntity<>("{\"items\":" + "}", HttpStatus.OK);
    }

    @RequestMapping("/heroes")
    public ResponseEntity<?> heroes() {
        return new ResponseEntity<>("{\"heroes\":" + "}", HttpStatus.OK);
    }
}
