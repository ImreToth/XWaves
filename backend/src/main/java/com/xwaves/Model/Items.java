package com.xwaves.Model;

import com.google.gson.Gson;

public class Items {
    private String name;
    private String wear;
    private String ability;
    private Integer abilityvalue;
    private String imagePath;

    public Items() {
    }

    public Items(String name, String wear, String ability, Integer abilityvalue) {
        this.name = name;
        this.wear = wear;
        this.ability = ability;
        this.abilityvalue = abilityvalue;
        this.imagePath=this.getClass().getResource("/Assets/items/64/"+name+".png").getPath();
    }

    public String getName() {
        return name;
    }

    public String getWear() {
        return wear;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setWear(String wear) {
        this.wear = wear;
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

    @Override
    public String toString() {
        Gson json = new Gson();
        return json.toJson(this);
    }
    
    
}
