package com.xwaves.domain;

import com.google.gson.Gson;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String gamemaster;
    private String player1;
    private String player2;
    private String player3;
    private int playerNumber;
    
    public Games(){
    }

    public Games(String name, String gamemaster) {
        this.name = name;
        this.gamemaster = gamemaster;
        this.playerNumber=0;
    }

    public Games(String name, String gamemaster, String player1, String player2, String player3) {
        this.name = name;
        this.gamemaster = gamemaster;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.playerNumber=3;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGamemaster() {
        return gamemaster;
    }

    public void setGamemaster(String gamemaster) {
        this.gamemaster = gamemaster;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getPlayer3() {
        return player3;
    }

    public void setPlayer3(String player3) {
        this.player3 = player3;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    } 
    @Override
    public String toString() {
        Gson json = new Gson();
        return json.toJson(this);
    }
      
}
