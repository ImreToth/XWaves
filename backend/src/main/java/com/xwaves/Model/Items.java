package com.xwaves.Model;

public class Items {
    private String name;
    private String wear;
    private String ability;
    private Integer abilityvalue;

    public Items() {
    }

    public Items(String name, String wear, String ability, Integer abilityvalue) {
        this.name = name;
        this.wear = wear;
        this.ability = ability;
        this.abilityvalue = abilityvalue;
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

    @Override
    public String toString() {
        return "Items{" + "name=" + name + ", wear=" + wear + ", ability=" + ability + ", abilityvalue=" + abilityvalue + '}';
    }
    
    
}
