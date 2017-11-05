package com.xwaves.Db;

import com.ibatis.common.jdbc.ScriptRunner;
import com.xwaves.Model.Monsters;
import com.xwaves.Model.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DB {
    final String JDBC_Driver = "com.mysql.jdbc.Driver";
    final String URL ="jdbc:mysql://localhost/Xwaves";
    final String USERNAME="root";
    final String PASSWORD="";
    final String UsersSchemaPath=this.getClass().getResource("/SqlScripts/UsersTable.sql").getPath();
    final String GameTablesSchemaPath = this.getClass().getResource("/SqlScripts/GameTables.sql").getPath();
    final String GameDatasPath = this.getClass().getResource("/SqlScripts/GameDatas.sql").getPath();
        
    Connection conn = null;
    Statement createStatement=null;
    DatabaseMetaData dbmd=null;
    PreparedStatement pstmt = null;
    
    public DB(){
        
        try {
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Connection is Done!");
        } catch (SQLException ex) {
            System.err.println(""+ex);
        }
        
        if(conn!=null){
            try {
                createStatement = conn.createStatement();
                System.out.println("Statement is Done!");
            } catch (SQLException ex) {
                System.err.println(""+ex);
            }
        }
        
        try {
            dbmd=conn.getMetaData();
            System.out.println("DatabaseMetaData is Done!");
        } catch (SQLException ex) {
                System.err.println(""+ex);
        }
      }
    
    public void CreateUsersTables(){
        try {
            ResultSet rs1 = dbmd.getTables(null, "APP", "Users", null);
            if(!rs1.next()){
                ExecuteSQLScript(UsersSchemaPath);
                System.out.println("Create Table");
            }
        } catch (SQLException ex) {
            System.err.println(""+ex);
        }
    }
    
    public void CreateGameTables(){
        try {
            ResultSet rs1 = dbmd.getTables(null, "APP", "Heroes", null);
            ResultSet rs2 = dbmd.getTables(null, "APP", "Items", null);
            ResultSet rs3 = dbmd.getTables(null, "APP", "Quests", null);
            ResultSet rs4 = dbmd.getTables(null, "APP", "Monsters", null);
            if (!rs1.next()&&!rs2.next()&&!rs3.next()&&!rs4.next()) {
                ExecuteSQLScript(GameTablesSchemaPath);
                ExecuteSQLScript(GameDatasPath);
            }
        } catch (SQLException ex) {
            System.err.println("" + ex);
        }
    }
    
    public void ExecuteSQLScript(String SqlScript) {
        try {
            ScriptRunner sr = new ScriptRunner(conn, false, false);
            Reader reader = new BufferedReader(new FileReader(SqlScript));            
            sr.runScript(reader);
        } catch (IOException | SQLException ex) {
            System.err.println("" + ex);
        }
    }
           
    public void addUser(User user){
       boolean notExist = true;
       ArrayList<User> users = this.getAllUsers();
       for (User u : users){
              if(u.getUsername().equals(user.getUsername()) || u.getEmail().equals(user.getEmail())){
                notExist=false;
                }
              
            }
       if(notExist){
        String sql="insert into Users ( username ,password,email,regtime) values(?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRegtime());
            pstmt.execute();
        } catch (SQLException ex) {
            System.err.println(""+ex);
            }
       
        }
    }
    
    public ArrayList<User> getAllUsers(){
        String sql = "select * from Users";
        ArrayList<User> users = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            users = new ArrayList<>();
            
            while (rs.next()){
                User actualUser = new User(rs.getString("username"),rs.getString("password"),rs.getString("email"));
                users.add(actualUser);
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van a userek kiolvasásakor");
            System.out.println(""+ex);
        }
      return users;
    }
    
    public void findByUsername(String username,User user){
      ArrayList<User> users = this.getAllUsers();
      for (User u : users){
          if(u.getUsername().equals(username)){
              user.setUsername(u.getUsername()); 
              user.setPassword(u.getPassword()); 
              user.setEmail(u.getEmail());
              user.setRegtime(u.getRegtime());
              break;
          }
      }
      if(user.getUsername() == null)
        System.out.println("User is not exists.");
    }
    
    public void findByEmail(String email,User user){
      ArrayList<User> users = this.getAllUsers();
      for (User u : users){
          if(u.getEmail().equals(email)){
              user.setUsername(u.getUsername()); 
              user.setPassword(u.getPassword()); 
              user.setEmail(u.getEmail());
              user.setRegtime(u.getRegtime());
              break;
          }
      }
      if(user.getEmail()== null)
        System.out.println("User is not exists.");
    }
    
    public ArrayList<Monsters> getAllMonsters(){
        String sql = "select * from Monsters";
        ArrayList<Monsters> monsters = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            monsters = new ArrayList<>();
            
            while (rs.next()){
                Monsters actualmonster = 
                    new Monsters(rs.getString("name"),rs.getString("attacktype"),rs.getInt("attack"),rs.getInt("health"),rs.getInt("stamina"),rs.getInt("defense"),rs.getInt("speed"));
                monsters.add(actualmonster);
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van a monsterek kiolvasásakor");
            System.out.println(""+ex);
        }
      return monsters;
    }
    
    public void findMonster(String name,Monsters monster){
      ArrayList<Monsters> monsters = this.getAllMonsters();
      for (Monsters m : monsters){
          if(m.getName().equals(name)){
              monster.setName(m.getName());
              monster.setAttacktype(m.getAttacktype());
              monster.setAttack(m.getAttack());
              monster.setHealth(m.getHealth());
              monster.setDefense(m.getDefense());
              monster.setStamina(m.getStamina());
              monster.setSpeed(m.getSpeed());
              break;
          }
      }
      if(monster.getName()== null)
        System.out.println("Monster is not exists.");
    }
    
    
}