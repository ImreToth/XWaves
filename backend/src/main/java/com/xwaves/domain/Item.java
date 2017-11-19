package com.xwaves.domain;


import com.google.gson.Gson;
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
    private Integer abilityvalue;
    private Integer useable;
    private Integer cost;
    private String imagePath;

    public Item() {
    }

    public Item(String name, String target, String ability, Integer abilityvalue,Integer useable,Integer cost) {
        this.name = name;
        this.target = target;
        this.ability = ability;
        this.abilityvalue = abilityvalue;
        this.useable = useable;
        this.cost = cost;
        this.imagePath=this.getClass().getResource("/Assets/items/64/"+name+".png").getPath();
    }

    public String getName() {
        return name;
    }

    public String getTarget() {
        return target;
    }

    public String getAbility() {
        return ability;
    }

    public Integer getAbilityvalue() {
        return abilityvalue;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Integer getUseable() {
        return useable;
    }

    public Integer getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public void setAbilityvalue(Integer abilityvalue) {
        this.abilityvalue = abilityvalue;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setUseable(Integer useable) {
        this.useable = useable;
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
