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
import com.xwaves.domain.User;

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
            
            pstmt = conn.prepareStatement(getItemSchema(gameName));
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
                + "`cost` int(10) ,"
                + "`X` int(10),"
                + "`Y` int(10),"
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
                + "`X` int(10),"
                + "`Y` int(10),"
                + "`item1` varchar(255),"
                + "`item2` varchar(255),"
                + "`item3` varchar(255),"
                + "PRIMARY KEY( `id` ));";
        return HeroSchema;
    }
    public String getItemSchema(String tableName){
        String Name=tableName+"_Item";
        String ItemSchema = "CREATE TABLE IF NOT EXISTS "+Name+" (\n"
            + "	`id` int(10) NOT NULL auto_increment,\n"
            + "	`name` varchar(255),\n"
            + "	`target` varchar(255),\n"
            + "	`ability` varchar(255),\n"
            + "	`abilityvalue` int(10),\n"
            + "	`useable` int(10),\n"
            + "        `cost` int(10) ,\n"
            + "	PRIMARY KEY( `id` )\n"
            + ");";
        return ItemSchema;
    }
}
