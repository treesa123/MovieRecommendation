package com.treesa.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TopMovieDetails {

	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/DbMovie";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "mjet";
	   
	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected database successfully...");
	      
	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();

	      String sql = "SELECT Moviename, Actor, Language,Rating  FROM MovieList ORDER BY Rating DESC LIMIT 5 ";
	      ResultSet rs = stmt.executeQuery(sql);
	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         
	         String Moviename = rs.getString("Moviename");
	         String Actor = rs.getString("Actor");
	         String Language = rs.getString("Language");
	         int Rating  = rs.getInt("Rating");
	         
	         //Display values
	         System.out.print(", Moviename: " + Moviename);
	         System.out.print(", Actor: " + Actor);
	         System.out.println(", Language: " + Language);
	         System.out.println(", Rating: " + Rating);

	      }
	      rs.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end main
	}//end JDBCExample


