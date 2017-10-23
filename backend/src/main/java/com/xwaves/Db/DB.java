package com.xwaves.Db;

import com.xwaves.Model.User;
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
    final String Schema = "CREATE TABLE IF NOT EXISTS `Users` (`id` int(10) NOT NULL auto_increment,`username` varchar(255),`password` varchar(255),`email` varchar(255),`regtime` date,PRIMARY KEY( `id` ))";
    
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
        try {
            ResultSet rs1 = dbmd.getTables(null, "APP", "Users", null);
            if(!rs1.next()){
                createStatement.execute(Schema);
                System.out.println("Create Table");
            }
        } catch (SQLException ex) {
            System.err.println(""+ex);
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
  
}