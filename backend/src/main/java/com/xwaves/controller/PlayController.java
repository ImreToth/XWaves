package com.xwaves.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xwaves.db.DB;
import com.xwaves.domain.Fight;
import com.xwaves.domain.Games;
import com.xwaves.domain.HeroSchema;
import com.xwaves.domain.MonsterSchema;
import com.xwaves.service.GamesService;
import java.util.ArrayList;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/play")

public class PlayController {

    private GamesService gamesService;

    private DB db;
    private Gson gson;

    @Autowired
    public PlayController(GamesService gamesService) {
        this.gamesService = gamesService;
        db = new DB();
        gson = new Gson();
    }

    /*Next player*/
    @RequestMapping(value = "/next", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> next(@RequestBody String s) {
        JsonObject obj = new JsonParser().parse(s).getAsJsonObject();
        String username = obj.get("username").getAsString();
        String gameName = obj.get("gameName").getAsString();

        if (username.equals(gamesService.getByName(gameName).getNextPlayer())) {
            return new ResponseEntity<>("true", HttpStatus.OK);
        }
        return new ResponseEntity<>("false", HttpStatus.OK);
    }

    /*Creator and Player games*/
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> search(@RequestBody String s) {
        JsonObject json = new JsonParser().parse(s).getAsJsonObject();
        String username = json.get("username").getAsString();

        TreeSet<Games> all = new TreeSet<Games>();
        
        JsonArray array = new JsonArray();

        for (Games g : gamesService.getByPlayer1(username)) {
            all.add(g);
        }

        for (Games g : gamesService.getByPlayer2(username)) {
            all.add(g);
        }

        for (Games g : gamesService.getByPlayer3(username)) {
            all.add(g);
        }
        
        for (Games g : all){
            JsonObject obj = new JsonParser().parse(gson.toJson(g)).getAsJsonObject();
            if(g.getNextPlayer().equals(username)) {
                obj.addProperty("nextPlayer", true);
            }else{
                obj.addProperty("nextPlayer", false);
            }
            array.add(obj);
        }

        return new ResponseEntity<>("{\"games\": " + gson.toJson(array) + "}", HttpStatus.OK);
       // return new ResponseEntity<>(gson.toJson(array), HttpStatus.OK);
    }

    /*Round end*/
    @RequestMapping(value = "/end", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> end(@RequestBody String s) {
        JsonObject json = new JsonParser().parse(s).getAsJsonObject();
        String username = json.get("username").getAsString();
        String gameName = json.get("gamename").getAsString();
        int action = json.get("action").getAsInt();

        MonsterSchema monster = null;
        for (MonsterSchema m : db.getAllMonsters(gameName)) {
            if (m.getPosition() == action) {
                monster = m;
            }
        }
        HeroSchema hero = null;
        for (HeroSchema h : db.getAllHeros(gameName)) {
            if (h.getUsername().equals(username)) {
                hero = h;
            }
        }

        if (monster != null && hero != null) {
            new Fight(monster, hero).doBattle(gameName);
        } else if (hero != null) {
            hero.setPosition(action);
            db.updateHero(gameName, hero);
        }
        
        if(db.getAllHeros(gameName).isEmpty()) {
            db.deleteGameTables(gameName);
            gamesService.delete(gamesService.getByName(gameName));
            return new ResponseEntity<>("LOSER", HttpStatus.OK);            
        }
        
        if(db.getAllMonsters(gameName).isEmpty()) {
            db.deleteGameTables(gameName);
            gamesService.delete(gamesService.getByName(gameName));
            return new ResponseEntity<>("WINNER", HttpStatus.OK);
        }

        if (gamesService.getByName(gameName).getPlayer1() == username) {
            gamesService.updateNextPlayer(gameName, gamesService.getByName(username).getPlayer2());
            return new ResponseEntity<>("2", HttpStatus.OK);
        } else if (gamesService.getByName(gameName).getPlayer2() == username) {
            gamesService.updateNextPlayer(gameName, gamesService.getByName(username).getPlayer3());
            return new ResponseEntity<>("3", HttpStatus.OK);
        } else if (gamesService.getByName(gameName).getPlayer3() == username) {
            gamesService.updateNextPlayer(gameName, gamesService.getByName(username).getPlayer1());
            return new ResponseEntity<>("1", HttpStatus.OK);
        }
        
        return new ResponseEntity<>("0", HttpStatus.OK);
    }

    /*Play game*/
    @RequestMapping(value = "/start", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> start(@RequestBody String s) {
        JsonObject json = new JsonParser().parse(s).getAsJsonObject();
        String gameName = json.get("gamename").getAsString();

        ArrayList<MonsterSchema> monsters = db.getAllMonsters(gameName);
        ArrayList<HeroSchema> heroes = db.getAllHeros(gameName);
        JsonObject obj = new JsonObject();
        obj.add("heroes", new JsonParser().parse(gson.toJson(heroes)));
        obj.add("monsters", new JsonParser().parse(gson.toJson(monsters)));

        return new ResponseEntity<>(gson.toJson(obj), HttpStatus.OK);
    }
}
