package com.xwaves.controllers;

import com.xwaves.Model.User;
import com.xwaves.Db.DB;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
            System.out.println("Felhasználónév és az e-mail cím foglalt!");
            return "Felhasználónév és az e-mail cím foglalt!";
        } else if (db.isUsername(user.getUsername())) {
            System.out.println("Felhasználónév foglalt!");
            return "Felhasználónév foglalt!";
        } else if (db.isEmail(user.getEmail())) {
            System.out.println("E-mail cím foglalt!");
            return "E-mail cím foglalt!";
        } else {
            db.CreateUsersTables();
            db.addUser(user);
            System.out.println("Sikeresen regisztráltál!");
            return "Sikeresen regisztráltál!";
        }
    }
}
