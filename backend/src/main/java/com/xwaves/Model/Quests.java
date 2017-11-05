package com.xwaves.Model;

import com.xwaves.XwavesApplication;


public class Quests {
    private String name;
    private String story;
    private Monsters monster1 = new Monsters();
    private Monsters monster2 = new Monsters();
    private Monsters monster3 = new Monsters();
    private Monsters monster4 = new Monsters();

    public Quests() {
    }

    public Quests(String name, String story, String monster1name) {
        this.name = name;
        this.story = story;
        XwavesApplication.db.findMonster(monster1name, this.monster1);
    }

    public Quests(String name, String story, String monster1name, String monster2name) {
        this.name = name;
        this.story = story;
        XwavesApplication.db.findMonster(monster1name, this.monster1);
        XwavesApplication.db.findMonster(monster2name, this.monster2);
    }

    public Quests(String name, String story, String monster1name, String monster2name, String monster3name) {
        this.name = name;
        this.story = story;
        XwavesApplication.db.findMonster(monster1name, this.monster1);
        XwavesApplication.db.findMonster(monster2name, this.monster2);
        XwavesApplication.db.findMonster(monster3name, this.monster3);
    }

    public Quests(String name, String story, String monster1name, String monster2name, String monster3name, String monster4name) {
        this.name = name;
        this.story = story;
        XwavesApplication.db.findMonster(monster1name, this.monster1);
        XwavesApplication.db.findMonster(monster2name, this.monster2);
        XwavesApplication.db.findMonster(monster3name, this.monster3);
        XwavesApplication.db.findMonster(monster4name, this.monster4);
    }

    public String getName() {
        return name;
    }

    public String getStory() {
        return story;
    }

    public Monsters getMonster1() {
        return monster1;
    }

    public Monsters getMonster2() {
        return monster2;
    }

    public Monsters getMonster3() {
        return monster3;
    }

    public Monsters getMonster4() {
        return monster4;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public void setMonster1(Monsters monster1) {
        this.monster1 = monster1;
    }

    public void setMonster2(Monsters monster2) {
        this.monster2 = monster2;
    }

    public void setMonster3(Monsters monster3) {
        this.monster3 = monster3;
    }

    public void setMonster4(Monsters monster4) {
        this.monster4 = monster4;
    }

    @Override
    public String toString() {
        return "Quests{" + "name=" + name + ", story=" + story + ", monster1=" + monster1 + ", monster2=" + monster2 + ", monster3=" + monster3 + ", monster4=" + monster4 + '}';
    }
    
    
    
    
    
    
    
    
}
