package com.xwaves.Model;

public class Heroes {
    private String name;
    private String type;
    private Integer attack;
    private Integer health;
    private Integer stamina;
    private Integer defense;
    private Integer speed;    
    private String imagePath;

    public Heroes() {
    }

    public Heroes(String name, String type, Integer attack, Integer health, Integer stamina, Integer defense, Integer speed) {
        this.name = name;
        this.type = type;
        this.attack = attack;
        this.health = health;
        this.stamina = stamina;
        this.defense = defense;
        this.speed = speed;
        this.imagePath = this.getClass().getResource("/Assets/heroes/Basic/hero_" + name + ".png").getPath();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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
    
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "Heroes{" + "name=" + name + ", type=" + type + ", attack=" + attack + ", health=" + health + ", stamina=" + stamina + ", defense=" + defense + ", speed=" + speed + '}';
    }
    
    
    
}
