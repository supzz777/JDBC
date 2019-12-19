package com.bridgelabz.jdbc.callableStatementsWithParameters;
import java.sql.DriverManager;
import java.sql.Types;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;

public class OUTparametersCallableStatements
{
	public static void main(String[] args)
	{
		
		Connection con  = null;
		CallableStatement smt = null;
		
		 
		String url ="jdbc:mysql://localhost:3306/demo" ;
		String user ="root";
		String password = "password";
		 
		 try 
		 { 
			 //get the connection
			 con = (Connection) DriverManager.getConnection(url,user,password);
			 
			 //prepare the stored procedure call
			 smt=(CallableStatement) con.prepareCall("  { call get_count_of_dept ( ?, ? )  } ");
			 
			 String dept ="IT";
			 smt.setString(1, dept);
			 smt.registerOutParameter(2, Types.INTEGER);
			 
			 //calling the stored procedure.
			 System.out.println("Calling the stored procedure");
			 
			 smt.execute();
			 
			 System.out.println("Finishing off with the stored procedure.");
			 
			 //get the value of out 
			 int c = smt.getInt(2);
			 
			 System.out.println("the count of the dept is:"+c);
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}
	
	

}
