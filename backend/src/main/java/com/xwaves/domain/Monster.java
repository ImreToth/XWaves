package com.xwaves.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String name;
    private String attacktype;
    private int attack;
    private int health;
    private int stamina;
    private int defense;
    private int speed;
    private int cost;
    private String path;

    public Monster() {
    }

    public Monster(long id, String name, String attacktype, int attack, int health, int stamina, int defense, int speed, int cost, String path) {
        this.id = id;
        this.name = name;
        this.attacktype = attacktype;
        this.attack = attack;
        this.health = health;
        this.stamina = stamina;
        this.defense = defense;
        this.speed = speed;
        this.cost = cost;
        this.path = path;
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

    public String getAttacktype() {
        return attacktype;
    }

    public void setAttacktype(String attacktype) {
        this.attacktype = attacktype;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
}
