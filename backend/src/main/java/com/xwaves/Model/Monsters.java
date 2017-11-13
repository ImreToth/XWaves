package com.xwaves.Model;

import com.google.gson.Gson;

public class Monsters {
    private String name;
    private String attacktype;
    private Integer attack;
    private Integer health;
    private Integer stamina;
    private Integer defense;
    private Integer speed;
    private Integer cost;
    private String imagePath;

    public Monsters() {
    }

    public Monsters(String name, String attacktype, Integer attack, Integer health, Integer stamina, Integer defense, Integer speed,Integer cost) {
        this.name = name;
        this.attacktype = attacktype;
        this.attack = attack;
        this.health = health;
        this.stamina = stamina;
        this.defense = defense;
        this.speed = speed;
        this.cost = cost;                
        this.imagePath = this.getClass().getResource("/Assets/monsters/Basic/monster_" + name + ".png").getPath();
    }

    public String getName() {
        return name;
    }

    public String getAttacktype() {
        return attacktype;
    }

    public Integer getAttack() {
        return attack;
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getStamina() {
        return stamina;
    }

    public Integer getDefense() {
        return defense;
    }

    public Integer getSpeed() {
        return speed;
    }

    public String getimagePath() {
        return imagePath;
    }

    public Integer getCost() {
        return cost;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setAttacktype(String attacktype) {
        this.attacktype = attacktype;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public void setStamina(Integer stamina) {
        this.stamina = stamina;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public void setimagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
    

    @Override
    public String toString() {
        Gson json = new Gson();
        return json.toJson(this);
    }
    
    
    
}
