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
    private String ranged;
    private String malee;
    private String support;
    private int playerNumber;

    public Games(String name, String gamemaster) {
        this.name = name;
        this.gamemaster = gamemaster;
        this.playerNumber=0;
    }

    public Games(String name, String gamemaster, String ranged, String malee, String support) {
        this.name = name;
        this.gamemaster = gamemaster;
        this.ranged = ranged;
        this.malee = malee;
        this.support = support;
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

    public String getRanged() {
        return ranged;
    }

    public void setRanged(String ranged) {
        this.ranged = ranged;
    }

    public String getMalee() {
        return malee;
    }

    public void setMalee(String malee) {
        this.malee = malee;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
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
