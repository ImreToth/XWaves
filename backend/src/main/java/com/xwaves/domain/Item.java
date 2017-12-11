package com.xwaves.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String name;
    private String target;
    private String ability;
    private int abilityvalue;
    private int useable;
    private int cost;
    private String path;

    public Item() {
    }

    public Item(String name, String target, String ability, int abilityvalue, int useable, int cost, String path) {
        this.name = name;
        this.target = target;
        this.ability = ability;
        this.abilityvalue = abilityvalue;
        this.useable = useable;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getAbilityvalue() {
        return abilityvalue;
    }

    public void setAbilityvalue(int abilityvalue) {
        this.abilityvalue = abilityvalue;
    }

    public int getUseable() {
        return useable;
    }

    public void setUseable(int useable) {
        this.useable = useable;
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
