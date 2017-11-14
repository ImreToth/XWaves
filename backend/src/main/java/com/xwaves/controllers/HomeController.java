package com.xwaves.controllers;

import com.google.gson.Gson;
import com.xwaves.Model.User;
import com.xwaves.Db.DB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    DB db = new DB();

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public String login(@RequestBody User user) {
        if (db.isUsername(user.getUsername())) {
            User tmp = new User();
            db.findByUsername(user.getUsername(), tmp);
            if (user.getPassword().equals(tmp.getPassword())) {
                System.out.println(tmp.toString());
                return tmp.toString();
            } else {
                System.out.println("Helytelen jelszó!");
                return "Helytelen jelszó!";
            }
        } else {
            System.out.println("Nincs ilyen felhasználó!");
            return "Nincs ilyen felhasználó!";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public String register(@RequestBody User user) {
        if (db.isUsername(user.getUsername()) && db.isEmail(user.getEmail())) {
            return "Felhasználónév és az e-mail cím foglalt!";
        } else if (db.isUsername(user.getUsername())) {
            return "Felhasználónév foglalt!";
        } else if (db.isEmail(user.getEmail())) {
            return "E-mail cím foglalt!";
        } else {
            db.addUser(user);
            return "Sikeresen regisztráltál!";
        }
    }

    @RequestMapping("/monster")
    public ResponseEntity<?> monster() {
        return new ResponseEntity<>(db.getAllHeroes(), HttpStatus.OK);
    }

    @RequestMapping("/items")
    public ResponseEntity<?> items() {
        return new ResponseEntity<>(db.getAllItems(), HttpStatus.OK);
    }

    @RequestMapping("/heroes")
    public ResponseEntity<?> heroes() {
        return new ResponseEntity<>(db.getAllHeroes(), HttpStatus.OK);
    }
}
