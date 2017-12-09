package com.xwaves.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xwaves.db.DB;
import com.xwaves.domain.Fight;
import com.xwaves.domain.Games;
import com.xwaves.domain.Hero;
import com.xwaves.domain.HeroSchema;
import com.xwaves.domain.Item;
import com.xwaves.domain.Monster;
import com.xwaves.domain.MonsterSchema;
import com.xwaves.domain.User;
import com.xwaves.service.UserService;
import com.xwaves.service.HeroService;
import com.xwaves.service.ItemService;
import com.xwaves.service.MonsterService;
import com.xwaves.service.GamesService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
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

    private DB db;
    private Gson gson;

    @Autowired
    public HomeController(UserService userService, HeroService heroService, ItemService itemService, MonsterService monsterService, GamesService gamesService) {
        this.userService = userService;
        this.heroService = heroService;
        this.itemService = itemService;
        this.monsterService = monsterService;
        this.gamesService = gamesService;
        db = new DB();
        gson = new Gson();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody User user) {
        if (userService.getByUsername(user.getUsername()) != null) {
            if (userService.getByUsername(user.getUsername()).getPassword().equals(user.getPassword())) {
                System.out.println("Sikeres belépés!");
                String jwtToken = Jwts.builder().setSubject(user.getEmail()).claim("roles", "user").setIssuedAt(new Date())
                        .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
                System.out.println(jwtToken);
                return new ResponseEntity<>(jwtToken, HttpStatus.OK);
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
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).setPath("/monsters/Basic/monster_" + monsters.get(i).getName() + ".png");
        }
        return new ResponseEntity<>("{\"monsters\": " + json.toJson(monsters) + "}", HttpStatus.OK);
    }

    @RequestMapping("/heroes")
    public ResponseEntity<?> heroes() {
        Gson json = new Gson();
        List<Hero> heroes = heroService.getAll();
        for (int i = 0; i < heroes.size(); i++) {
            heroes.get(i).setPath("/heroes/Basic/hero_" + heroes.get(i).getName() + ".png");
        }
        return new ResponseEntity<>("{\"heroes\": " + json.toJson(heroes) + "}", HttpStatus.OK);
    }

    @RequestMapping("/items")
    public ResponseEntity<?> items() {
        Gson json = new Gson();
        List<Item> items = itemService.getAll();
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setPath("/items/64/" + items.get(i).getName() + ".png");
        }
        return new ResponseEntity<>("{\"items\": " + json.toJson(items) + "}", HttpStatus.OK);
    }

    @RequestMapping("/games/search")
    public ResponseEntity<?> games() {
        Gson json = new Gson();
        return new ResponseEntity<>("{\"games\": " + json.toJson(gamesService.getAll()) + "}", HttpStatus.OK);
    }

    @RequestMapping(value = "/games/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> register(@RequestBody String s) {
        JsonObject json = new JsonParser().parse(s).getAsJsonObject();
        String username = json.get("username").getAsString();
        String gameName = json.get("gamename").getAsString();
        JsonArray board = json.get("board").getAsJsonArray();

        ArrayList<MonsterSchema> monsters = new ArrayList<MonsterSchema>();

        for (int i = 0; i < board.size(); i++) {
            board.get(i).getAsJsonObject().addProperty("position", i);
            if(board.get(i).getAsJsonObject().get("attack").getAsInt() != 0) {
                monsters.add(new Gson().fromJson(board.get(i).getAsJsonObject(), MonsterSchema.class));
            }
        }
        db.createOneGameTable(gamesService, gameName, userService.getByUsername(username));
        db.saveMonsters(gameName, monsters);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/play/next", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> next(@RequestBody String s) {
        JsonObject json = new JsonParser().parse(s).getAsJsonObject();
        if (json.get("username").getAsString().equals(gamesService.getByName(json.get("gamename").getAsString()).getNextPlayer())) {
            return new ResponseEntity<>("true", HttpStatus.OK);
        }
        return new ResponseEntity<>("false", HttpStatus.OK);
    }

    @RequestMapping(value = "/play/search", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> userGame(@RequestBody String s) {
        JsonObject json = new JsonParser().parse(s).getAsJsonObject();
        String username = json.get("username").getAsString();
        Gson gson = new Gson();
        TreeSet<JsonObject> all = new TreeSet<JsonObject>();
        JsonArray array = new JsonArray();

        for (Games g : gamesService.getByNextPlayer(username)) {
            JsonObject obj = new JsonParser().parse(new Gson().toJson(g)).getAsJsonObject();
            obj.addProperty("nextPlayer", "true");

            array.add(obj);
        }

        for (Games g : gamesService.getByPlayer1(username)) {
            JsonObject obj = new JsonParser().parse(new Gson().toJson(g)).getAsJsonObject();
            obj.addProperty("nextPlayer", "false");
            array.add(obj);
        }

        for (Games g : gamesService.getByPlayer2(username)) {
            JsonObject obj = new JsonParser().parse(new Gson().toJson(g)).getAsJsonObject();
            obj.addProperty("nextPlayer", "false");
            array.add(obj);
        }

        for (Games g : gamesService.getByPlayer3(username)) {
            JsonObject obj = new JsonParser().parse(new Gson().toJson(g)).getAsJsonObject();
            obj.addProperty("nextPlayer", "false");
            array.add(obj);
        }

        for (JsonElement o : array) {
            all.add(o.getAsJsonObject());
        }

        return new ResponseEntity<>(new Gson().toJson(all), HttpStatus.OK);
    }

    @RequestMapping(value = "/games/join", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> join(@RequestBody String s) {
        JsonObject json = new JsonParser().parse(s).getAsJsonObject();
        int i = gamesService.joinPlayer(json.get("username").getAsString());
        json.get("hero").getAsJsonObject().addProperty("position", 93 - i);
        HeroSchema hero = new Gson().fromJson(json.get("hero"), HeroSchema.class);
        new DB().saveHero(json.get("username").getAsString(), hero);
        if (i == 3) {
            gamesService.updateNextPlayer(json.get("gamename").getAsString(), gamesService.getByName(json.get("gamename").getAsString()).getPlayer1());
            return new ResponseEntity<>("play", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(i + "", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/play/end", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
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
            new Fight(monster, hero);
        } else if (hero != null){
            hero.setPosition(action);
            db.updateHero(gameName, hero);
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
    
    @RequestMapping(value = "/play/start", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> start(@RequestBody String s) {
        JsonObject json = new JsonParser().parse(s).getAsJsonObject();
        String gameName = json.get("gameName").getAsString();
        
        ArrayList<MonsterSchema> monsters = db.getAllMonsters(gameName);
        ArrayList<HeroSchema> heroes = db.getAllHeros(gameName);
        JsonObject obj = new JsonObject();
        obj.add("heroes", new JsonParser().parse(gson.toJson(heroes)));        
        obj.add("monsters", new JsonParser().parse(gson.toJson(monsters)));
        
        return new ResponseEntity<>(gson.toJson(obj), HttpStatus.OK);
    }

    @RequestMapping(value = "/fight", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> fight(@RequestBody String s) {
        JsonArray json = new JsonParser().parse(s).getAsJsonArray();
        new DB().createOneGameTable(gamesService, "fight", new User(99, "fight", "fight", "fight", new Date()));
        ArrayList<MonsterSchema> monster = new ArrayList<MonsterSchema>();
        ArrayList<HeroSchema> hero = new ArrayList<HeroSchema>();
        monster.add(new Gson().fromJson(json.get(0), MonsterSchema.class));
        hero.add(new Gson().fromJson(json.get(1), HeroSchema.class));
        new DB().saveHero("fight", hero.get(0));
        new DB().saveMonsters("fight", monster);
        //TODO
        return new ResponseEntity<>("0", HttpStatus.OK);
    }
}
