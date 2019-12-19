package com.bridgelabz.jdbc.transactions;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.text.ParseException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class JDBCtransactions
{
	
	private static final String INSERT_SQL = "INSERT INTO employees "
	         + "(id, lname, fname ,email_id , dept , salary) VALUES (?,?,?,?,?,?)";

	   public static void main(String[] args) throws ParseException
	   {
		   
		   
	   
	      
	      String url ="jdbc:mysql://localhost:3306/demo" ; 
	      String user ="root";
		  String password = "password"; 
			  
			 
			
	      try ( Connection conn = (Connection) DriverManager.getConnection(url, user, password); ) 
	      {

		         // Disable auto commit mode
		         conn.setAutoCommit(false);
	
		         try (PreparedStatement insertStmt = (PreparedStatement) conn.prepareStatement(INSERT_SQL);) 
		         {
	
		            // Insert 1st record
		            insertStmt.setInt(1, 44 );
		            insertStmt.setString(2, "pramila");
		            insertStmt.setString(3, "khot");
		            insertStmt.setString(4, "dgfdy@gmail.com");
		            insertStmt.setString(5, "IT");
		            insertStmt.setInt(6, 34000);
		            insertStmt.executeUpdate();
		         
		           
	
		            // Insert 2st record
		            insertStmt.setInt(1, 45);
		            insertStmt.setString(2, "surbhi");
		            insertStmt.setString(3, "helve");
		            insertStmt.setString(4, "sdfds@gmail.com");
		            insertStmt.setString(5, "engineering");
		            insertStmt.setInt(6, 60000 );
		            insertStmt.executeUpdate();
		       
		            
		            // Insert 3st record
		            insertStmt.setInt(1, 46);
		            insertStmt.setString(2, "rani");
		            insertStmt.setString(3, "sashai");
		            insertStmt.setString(4, "sdgjk@gmail.com");
		            insertStmt.setString(5, "IT");
		            insertStmt.setInt(6, 76800);
		            insertStmt.executeUpdate();
		           
		            
		            
		            // Create Savepoint
		            Savepoint savepoint = conn.setSavepoint();
	
		            // Insert 4st record
		            insertStmt.setInt(1, 47 );
		            insertStmt.setString(2, "pramit");
		            insertStmt.setString(3, "kasnu");
		            insertStmt.setString(4, "uuyhij@gmail.com");
		            insertStmt.setString(5, "IT");
		            insertStmt.setInt(6, 9800 );
		            insertStmt.executeUpdate();
		           
		            
		            
		            
		            // Insert 5st record
		            insertStmt.setInt(1, 48 );
		            insertStmt.setString(2, "anand");
		            insertStmt.setString(3, "vijay");
		            insertStmt.setString(4, "u7tbt@gmail.com");
		            insertStmt.setString(5, "engineering");
		            insertStmt.setInt(6, 34500 );
		            insertStmt.executeUpdate();
		            
	
		            // Rollback to savepoint
		            conn.rollback(savepoint);
		            
		            // Commit statement
		            conn.commit();
	
		            System.out.println("Transaction is commited successfully.");
		         } 
		         catch ( SQLException e )
		         {
		            ((Throwable) e).printStackTrace();
			            if (conn != null) 
			            {
				               try
				               {
				                  // Roll back transaction
				                  System.out.println("Transaction is being rolled back.");
				                  conn.rollback();
				               } catch (Exception ex)
				               {
				                  ex.printStackTrace();
				               }
			            }
			            
		         }
		         
	      }
	      catch (SQLException e)
	      {
	         e.printStackTrace();
	      }
	      
	      
	      
	     
	      
	      
	      
	   }//main end
	   
	   
	   
	

}
