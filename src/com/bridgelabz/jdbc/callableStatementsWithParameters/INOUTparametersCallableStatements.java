package com.bridgelabz.jdbc.callableStatementsWithParameters;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;

public class INOUTparametersCallableStatements 
{
	public static void main(String[] args) 
	{
		
		Connection con = null;  //importing from com.mysql.jdbc.* .
		CallableStatement smt = null;
		
		String url ="jdbc:mysql://localhost:3306/demo" ;
		String user ="root"; 
		String password = "password";
		
		try 
		{
			//get connection to the database. 
			 con = (Connection) DriverManager.getConnection(url,user,password);
			 			
			//prepare the storred procedure call
			smt = (CallableStatement) con.prepareCall(" { call  greet_dept(?) }  ");
			
			String department = "IT";
			
			smt.setString(1, department);
			
			//calling the stored procedure.
			System.out.println("calling the stored procedure.");
			smt.execute();
			
			System.out.println("Finished calling stored procedure.");
			
			
			//storing the result in the variable
			String rs = smt.getString(1);
			
			System.out.println( "the result is:" +rs );
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
				
		
	}

}
