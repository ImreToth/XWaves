package com.xwaves.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xwaves.db.DB;
import com.xwaves.domain.HeroSchema;
import com.xwaves.domain.MonsterSchema;
import com.xwaves.service.GamesService;
import com.xwaves.service.UserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private UserService userService;
    private GamesService gamesService;

    private DB db;
    private Gson gson;

    @Autowired
    public GameController(UserService userService, GamesService gamesService) {
        this.userService = userService;
        this.gamesService = gamesService;
        db = new DB();
        gson = new Gson();
    }

    /*All games*/
    @RequestMapping("/search")
    public ResponseEntity<?> search() {
        return new ResponseEntity<>("{\"games\": " + gson.toJson(gamesService.getAll()) + "}", HttpStatus.OK);
    }

    /*Create game*/
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> create(@RequestBody String s) {
        JsonObject json = new JsonParser().parse(s).getAsJsonObject();
        String username = json.get("username").getAsString();
        String gameName = json.get("gamename").getAsString();
        JsonArray board = json.get("board").getAsJsonArray();

        ArrayList<MonsterSchema> monsters = new ArrayList<MonsterSchema>();

        for (int i = 0; i < board.size(); i++) {
            board.get(i).getAsJsonObject().addProperty("position", i);
            if (board.get(i).getAsJsonObject().get("attack").getAsInt() != 0) {
                monsters.add(gson.fromJson(board.get(i).getAsJsonObject(), MonsterSchema.class));
            }
        }
        db.createOneGameTable(gamesService, gameName, userService.getByUsername(username));
        db.saveMonsters(gameName, monsters);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /*Join game*/
    @RequestMapping(value = "/join", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> join(@RequestBody String s) {
        JsonObject json = new JsonParser().parse(s).getAsJsonObject();
        int i = gamesService.joinPlayer(json.get("username").getAsString());
        json.get("hero").getAsJsonObject().addProperty("position", 93 - i);
        HeroSchema hero = gson.fromJson(json.get("hero"), HeroSchema.class);
        db.saveHero(json.get("username").getAsString(), hero);
        if (i == 3) {
            gamesService.updateNextPlayer(json.get("gamename").getAsString(), gamesService.getByName(json.get("gamename").getAsString()).getPlayer1());
            return new ResponseEntity<>("play", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(i + "", HttpStatus.OK);
        }
    }

}
