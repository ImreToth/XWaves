package com.xwaves.domain;

import com.xwaves.db.DB;

public class Fight {
    private MonsterSchema monster;
    private MonsterSchema monsterForFight;
    private HeroSchema hero;
    private HeroSchema heroForFight;
    private final DB db = new DB();

    public Fight() {
    }

    public Fight(MonsterSchema monster, HeroSchema hero) {
        this.monster = monster;
        this.hero = hero;
        setAttributeForHero();
        setAttributeForMonster();
    }

    public MonsterSchema getMonster() {
        return monster;
    }

    public void setMonster(MonsterSchema monster) {
        this.monster = monster;
    }

    public HeroSchema getHero() {
        return hero;
    }

    public void setHero(HeroSchema hero) {
        this.hero = hero;
    }
    
    public void doBattle(String gamename){
        //Hero üt
        int strike = heroForFight.getAttack() - monsterForFight.getDefense();
        if(strike > 0){
            int currentHealth=monster.getHealth() - strike;
            monster.setHealth(currentHealth);
                if(monster.getHealth() > 0){
                    //Update to DB TODO
                    db.updateMonster(gamename, monster);
                    System.out.println("updateMonster");
                }else{
                    //DELETE from DB TODO and hero.setPosition(nextPosition) and hero.setHealth(hero.getHealth()+1)
                    db.deleteMonster(gamename, monster);
                    System.out.println("deleteMonster");
                    hero.setHealth(hero.getHealth() + 1);
                    hero.setPosition(hero.getNextPosition());
                    db.updateHero(gamename, hero);
                }
        }else{
            System.out.println("Miss...");
        }
        //Monster üt
        
        strike = monsterForFight.getAttack() - heroForFight.getDefense();
        if(strike > 0){
            int currentHealth=hero.getHealth() - strike;
            hero.setHealth(currentHealth);
                if(hero.getHealth() > 0){
                    //Update to DB TODO
                    db.updateHero(gamename, hero);
                    System.out.println("updateHero");
                }else{
                    //DELETE from DB TODO
                    db.deleteHero(gamename, hero);
                    System.out.println("deleteHero");
                }
        }else{
            System.out.println("Miss...");
        }
    }
    
    private void setAttributeForHero(){
        this.heroForFight=this.hero;
        System.out.println("com.xwaves.domain.Fight.setAttributeForHero()");
             
        switch (heroForFight.getType()) {
            case "melee":
                heroForFight.setAttack((int) (heroForFight.getAttack() * 1.5));
                heroForFight.setDefense((int)(heroForFight.getDefense()*1.5 + heroForFight.getStamina()*0.5));
                
                System.out.println("malee - > atk: " + heroForFight.getAttack() +"def:" +heroForFight.getDefense() );
                
                break;
            case "ranged":
                heroForFight.setAttack((int) (heroForFight.getAttack() + heroForFight.getSpeed()*0.5));
                heroForFight.setDefense((int)(heroForFight.getDefense() + heroForFight.getStamina()*1.25)); 
                
                System.out.println("ranged - > atk: " + heroForFight.getAttack() +"def:" +heroForFight.getDefense() );               
                break;
            case "magic":
                heroForFight.setAttack((int) (heroForFight.getAttack() * 1.25));
                heroForFight.setDefense((int)(heroForFight.getDefense() + heroForFight.getStamina())); 
                
                System.out.println("magic - > atk: " + heroForFight.getAttack() +"def:" +heroForFight.getDefense() );
                break;
            default:
                System.out.println("com.xwaves.domain.Fight.setAttributeForHero().break");
                break;
        }
    
    }

    private void setAttributeForMonster(){
        this.monsterForFight = this.monster;
        switch (monsterForFight.getAttacktype()) {
            case "melee":
                monsterForFight.setAttack((int) (monsterForFight.getAttack()*1.25));
                monsterForFight.setDefense((int) (monsterForFight.getDefense()+ monsterForFight.getStamina()*0.5));
                System.out.println("malee - > atk: " + monsterForFight.getAttack() +"def:" +monsterForFight.getDefense() );
                break;
            case "ranged":
                monsterForFight.setAttack((int) (monsterForFight.getAttack()+monsterForFight.getSpeed()*0.25));
                monsterForFight.setDefense((int) (monsterForFight.getDefense()+ monsterForFight.getStamina()));
                System.out.println("ranged - > atk: " + monsterForFight.getAttack() +"def:" +monsterForFight.getDefense() );
                break;
            case "magic":
                monsterForFight.setAttack((int) (monsterForFight.getAttack()*1.15));
                monsterForFight.setDefense((int) (monsterForFight.getDefense()+ monsterForFight.getStamina()*0.75));
                System.out.println("magic - > atk: " + monsterForFight.getAttack() +"def:" +monsterForFight.getDefense() );                
                break;
            default:
                break;
        }
    }
    
}
