package com.xwaves;

import com.xwaves.Db.DB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XwavesApplication {
    public static DB db = new DB();

	public static void main(String[] args) {
		SpringApplication.run(XwavesApplication.class, args);    
		db.CreateUsersTables();
                db.DropGameTables();
                db.CreateGameTables();
	}
}
