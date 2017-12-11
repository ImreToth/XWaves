package com.xwaves.controller;

import com.google.gson.Gson;
import com.xwaves.domain.Hero;
import com.xwaves.domain.Item;
import com.xwaves.domain.Monster;
import com.xwaves.service.HeroService;
import com.xwaves.service.ItemService;
import com.xwaves.service.MonsterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    private HeroService heroService;
    private ItemService itemService;
    private MonsterService monsterService;

    private Gson gson;

    @Autowired
    public HomeController(HeroService heroService, ItemService itemService, MonsterService monsterService) {
        this.heroService = heroService;
        this.itemService = itemService;
        this.monsterService = monsterService;
        gson = new Gson();
    }

    /*Get monsters*/
    @RequestMapping("/monsters")
    public ResponseEntity<?> monsters() {
        List<Monster> monsters = monsterService.getAll();
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).setPath("/monsters/Basic/monster_" + monsters.get(i).getName() + ".png");
        }
        return new ResponseEntity<>("{\"monsters\": " + gson.toJson(monsters) + "}", HttpStatus.OK);
    }

    /*Get heroes*/
    @RequestMapping("/heroes")
    public ResponseEntity<?> heroes() {
        List<Hero> heroes = heroService.getAll();
        for (int i = 0; i < heroes.size(); i++) {
            heroes.get(i).setPath("/heroes/Basic/hero_" + heroes.get(i).getName() + ".png");
        }
        return new ResponseEntity<>("{\"heroes\": " + gson.toJson(heroes) + "}", HttpStatus.OK);
    }

    /*Get items*/
    @RequestMapping("/items")
    public ResponseEntity<?> items() {
        List<Item> items = itemService.getAll();
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setPath("/items/64/" + items.get(i).getName() + ".png");
        }
        return new ResponseEntity<>("{\"items\": " + gson.toJson(items) + "}", HttpStatus.OK);
    }
}
