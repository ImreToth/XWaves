package com.xwaves.controller;

import com.xwaves.domain.User;
import com.xwaves.service.UserService;
import com.xwaves.service.HeroService;
import com.xwaves.service.ItemService;
import com.xwaves.service.MonsterService;
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
    private HeroService heroService;
    private ItemService itemService;
    private MonsterService monsterService;

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
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Helytelen jelszó!");
            }
        } else {
            System.out.println("Nincs ilyen felhasználó!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Nincs ilyen felhasználó!");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.getByUsername(user.getUsername()) != null && userService.getByEmail(user.getEmail()) != null) {
            System.out.println("Felhasználó és email foglalt!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Felhasználónév és email cím már foglalt!");
        } else if (userService.getByUsername(user.getUsername()) != null) {
            System.out.println("Felhasználó foglalt!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Felhasználónév már foglalt!");
        } else if (userService.getByEmail(user.getEmail()) != null) {
            System.out.println("Email foglalt!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Email cím már foglalt!");
        } else {
            user.setDate(new Date());
            userService.save(user);
            System.out.println("Sikeres regisztráció!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Sikeres regisztráció!"); 
        }
    }

    @RequestMapping("/monsters")
    public ResponseEntity<?> monsters() {
        return new ResponseEntity<>("{\"monsters\":" + monsterService.getAll() +"}", HttpStatus.OK);
    }

    @RequestMapping("/items")
    public ResponseEntity<?> items() {
        return new ResponseEntity<>("{\"items\":" + itemService.getAll() + "}", HttpStatus.OK);
    }

    @RequestMapping("/heroes")
    public ResponseEntity<?> heroes() {
        return new ResponseEntity<>("{\"heroes\":" + heroService.getAll() + "}", HttpStatus.OK);
    }
}
