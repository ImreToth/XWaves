package com.xwaves.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.xwaves.service.GamesService;
import com.xwaves.domain.Games;
import com.xwaves.domain.Hero;
import com.xwaves.domain.HeroSchema;
import com.xwaves.domain.Monster;
import com.xwaves.domain.MonsterSchema;
import com.xwaves.domain.User;
import java.util.ArrayList;

public class DB {

    final String JDBC_Driver = "com.mysql.jdbc.Driver";
    final String URL = "jdbc:mysql://localhost/Xwaves";
    final String USERNAME = "root";
    final String PASSWORD = "";

    Connection conn = null;
    Statement createStatement = null;
    DatabaseMetaData dbmd = null;
    PreparedStatement pstmt = null;
    
    public DB() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection is Done!");
        } catch (SQLException ex) {
            System.err.println("" + ex);
        }

        if (conn != null) {
            try {
                createStatement = conn.createStatement();
                System.out.println("Statement is Done!");
            } catch (SQLException ex) {
                System.err.println("" + ex);
            }
        }

        try {
            dbmd = conn.getMetaData();
            System.out.println("DatabaseMetaData is Done!");
        } catch (SQLException ex) {
            System.err.println("" + ex);
        }
    }
        

    public void createOneGameTable(GamesService gamesService,String gameName,User creator) {
        try {
            Games g = new Games(gameName,creator.getUsername());
            
            gamesService.save(g);
            
            pstmt = conn.prepareStatement(getMonsterSchema(gameName));
            pstmt.execute();
            
            pstmt = conn.prepareStatement(getHeroSchema(gameName));
            pstmt.execute();
        } catch (SQLException ex) {
            System.err.println("" + ex);
        }
    }
    
    public void saveMonsters(String gamename , ArrayList<MonsterSchema> monsters) {
        int id=0;
        for(MonsterSchema m : monsters){
            String sql = "insert into "+ gamename +"_Monster (id,name,attacktype,attack,health,stamina,defense,speed,position) values(?,?,?,?,?,?,?,?,?)";
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,id+1);
                pstmt.setString(2,m.getName());
                pstmt.setString(3,m.getAttacktype());
                pstmt.setInt(4,m.getAttack());
                pstmt.setInt(5,m.getHealth());
                pstmt.setInt(6,m.getStamina());
                pstmt.setInt(7,m.getDefense());
                pstmt.setInt(8,m.getSpeed());
                pstmt.setInt(9,m.getPosition());
                pstmt.execute();
                id++;
            } catch (SQLException ex) {
                System.err.println("" + ex);
            } 
        }
    }
    
    public void saveHero(String gamename , HeroSchema h) {
            String sql = "insert into "+ gamename +"_Hero (id,name,type,attack,health,stamina,defense,speed,position) values(?,?,?,?,?,?,?,?,?)";
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setLong(1,h.getId());
                pstmt.setString(2,h.getName());
                pstmt.setString(3,h.getType());
                pstmt.setInt(4,h.getAttack());
                pstmt.setInt(5,h.getHealth());
                pstmt.setInt(6,h.getStamina());
                pstmt.setInt(7,h.getDefense());
                pstmt.setInt(8,h.getSpeed());
                pstmt.setInt(9,h.getPosition());
                pstmt.execute();
            } catch (SQLException ex) {
                System.err.println("" + ex);
            } 
        
    }
    
    public void updateMonster(String gamename,MonsterSchema m){
        String sql="UPDATE "+ gamename +"_Monster SET attack=?, health=?, stamina=?, defense=?, speed=?  WHERE position='"+m.getPosition()+"';";
        try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,m.getAttack());
                pstmt.setInt(2,m.getHealth());
                pstmt.setInt(3,m.getStamina());
                pstmt.setInt(4,m.getDefense());
                pstmt.setInt(5,m.getSpeed());
                pstmt.execute();
            } catch (SQLException ex) {
                System.err.println("" + ex);
            } 
    }
    
    public void updateHero(String gamename,HeroSchema h){
        String sql="UPDATE "+ gamename +"_Hero SET attack=?, health=?, stamina=?, defense=?, speed=?, position=?, nextposition=?,item1=?,item2=?,item3=?  WHERE name='"+h.getName()+"';";
        try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,h.getAttack());
                pstmt.setInt(2,h.getHealth());
                pstmt.setInt(3,h.getStamina());
                pstmt.setInt(4,h.getDefense());
                pstmt.setInt(5,h.getSpeed());
                pstmt.setInt(6,h.getPosition());
                pstmt.setInt(7, h.getNextPosition());
                pstmt.setString(8,h.getItem1());
                pstmt.setString(9,h.getItem2());
                pstmt.setString(10,h.getItem3());
                pstmt.execute();
            } catch (SQLException ex) {
                System.err.println("" + ex);
            } 
    }
    
    public void deleteGameTables(String name){
        String sql= "DROP TABLE "+name+"_Monster;"
                  + "DROP TABLE "+name+"_Hero;";
        try {
                pstmt = conn.prepareStatement(sql);
                pstmt.execute();
            } catch (SQLException ex) {
                System.err.println("" + ex);
            } 
        
    }
    
    public String getMonsterSchema(String tableName){
        String Name=tableName+"_Monster";
        String MonsterSchema = "CREATE TABLE IF NOT EXISTS " + Name +"("
                + "`id` int(10) NOT NULL auto_increment,"
                + "`name` varchar(255),"
                + "`attacktype` varchar(255),"
                + "`attack` int(10),"
                + "`health` int(10),"
                + "`stamina` int(10),"
                + "`defense` int(10),"
                + "`speed` int(10),"
                + "`position` int(10),"
                + "PRIMARY KEY( `id` ));";
        return MonsterSchema;
    }
    
    public String getHeroSchema(String tableName){
        String Name=tableName+"_Hero";
        String HeroSchema = "CREATE TABLE IF NOT EXISTS "+ Name +" ("
                + "`id` int(10) NOT NULL auto_increment,"
                + "`name` varchar(255),"
                + "`type` varchar(255),"
                + "`attack` int(10),"
                + "`health` int(10),"
                + "`stamina` int(10),"
                + "`defense` int(10),"
                + "`speed` int(10),"
                + "`position` int(10),"
                + "`nextposition` int(10),"
                + "`item1` varchar(255),"
                + "`item2` varchar(255),"
                + "`item3` varchar(255),"
                + "PRIMARY KEY( `id` ));";
        return HeroSchema;
    }
}
