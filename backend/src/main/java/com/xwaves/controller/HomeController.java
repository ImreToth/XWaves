package com.xwaves.controller;

import com.google.gson.Gson;
import com.xwaves.domain.Games;
import com.xwaves.domain.Hero;
import com.xwaves.domain.Item;
import com.xwaves.domain.Monster;
import com.xwaves.domain.User;
import com.xwaves.service.UserService;
import com.xwaves.service.HeroService;
import com.xwaves.service.ItemService;
import com.xwaves.service.MonsterService;
import com.xwaves.service.GamesService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private GamesService gamesService;
    
    private Gson json;
    
    @Autowired
    public HomeController(UserService userService, HeroService heroService, ItemService itemService, MonsterService monsterService, GamesService gamesService) {
        this.userService = userService;
        this.heroService = heroService;
        this.itemService = itemService;
        this.monsterService = monsterService;
        this.gamesService = gamesService;
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
        Gson json = new Gson();
        List<Monster> monsters = monsterService.getAll();
        for(int i = 0; i < monsters.size(); i++) {
            monsters.get(i).setPath("/monsters/Basic/monster_" + monsters.get(i).getName() + ".png");
        }
        return new ResponseEntity<>("{\"monsters\": "+ json.toJson(monsters) +"}", HttpStatus.OK);
    }

    @RequestMapping("/heroes")
    public ResponseEntity<?> heroes() {
        Gson json = new Gson();
        List<Hero> heroes = heroService.getAll();
        for(int i = 0; i < heroes.size(); i++) {
            heroes.get(i).setPath("/heroes/Basic/hero_" + heroes.get(i).getName() + ".png");
        }
        return new ResponseEntity<>("{\"heroes\": "+ json.toJson(heroes) +"}", HttpStatus.OK);
    }
    
    @RequestMapping("/items")
    public ResponseEntity<?> items() {
        Gson json = new Gson();
        List<Item> items = itemService.getAll();
        for(int i = 0; i < items.size(); i++) {
            items.get(i).setPath("/items/64/" + items.get(i).getName() + ".png");
        }
        return new ResponseEntity<>("{\"items\": "+ json.toJson(items) +"}", HttpStatus.OK);
    }
    
    @RequestMapping("/games/search")
    public ResponseEntity<?> games() {
        Gson json = new Gson();
        return new ResponseEntity<>(json.toJson(gamesService.getAll()), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/games/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> register(@RequestBody String s) {
        System.out.println(s);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
