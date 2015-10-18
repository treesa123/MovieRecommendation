package com.treesa.db;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.DataInputStream;
import java.io.InputStreamReader;


public class InsertMovieDetails {
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
    	      System.out.println("Inserting records into the table...");
    	      stmt = conn.createStatement();
    	      
    	      //////////////////////////////////////////////
    	      
    	      FileInputStream fstream = new FileInputStream("src/test/resources/Moviedata.txt");
              // Get the object of DataInputStream
              DataInputStream in = new DataInputStream(fstream);
              BufferedReader br = new BufferedReader(new InputStreamReader(in));
              
              String line = null;
              while( (line = br.readLine())!= null )
              {
                      // \\s+ means any number of whitespace between tokens
                  String [] tokens = line.split(" ");
        	      String a=tokens[0];
        	      String b=tokens[1];
        	      String c=tokens[2];
        	      String d = tokens[3];
        	      //String e= "tokens[3]";
        	      int i = Integer.parseInt(d.trim());
    	          String sql = "INSERT INTO MovieList " +
    	                   "VALUES ('"+a+"','"+b+"','"+c+"',"+i+")";
    	      stmt.executeUpdate(sql);
    	      
    	      
              }
    	    
    	      System.out.println("Inserted records into the table...");

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