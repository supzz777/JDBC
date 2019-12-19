package com.bridgelabz.jdbc.loginAndRegister;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

public class JDBCLoginAndRegister 
{
	static final String jdbcDriver = "com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost:3306/demo";
	static final String user = "root";
	static final String password = "password";
	
	static Connection con = null;
	static PreparedStatement smt1 = null;
	static ResultSet rs = null;
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception 
	{
		
		
		boolean exit = true;
		System.out.println("welcome");
		while(exit)
		{ 	
			
			System.out.println("enter your choice plz: ");
			System.out.println("1.register\n"
								+ "2.login\n"
								+ "3.exit");
			int number = sc.nextInt();
			
			switch(number)
			{
			case 1: System.out.println("register yourself into our database.");
					register();
					break;
					
			case 2: System.out.println("For login plz enter ur password");
					String password = sc.next();
					if(password.equals("supzz"))
					{
						login( smt1);
					}
					break;
					
			case 3: exit = false;
					System.out.println("BYE BYE TATA SEE YOU !!!");
					break;
					
			default: System.out.println("enter your choice correctly plz");	
			}
		}
		
	}//main end.
	
	
	//-----------------------------------//
	
	private static void login(PreparedStatement smt2) throws Exception //select*from employes using prepared Statements
	{
		Class.forName(jdbcDriver); // The specified class loader is used to load the class or interface.

		// Connection to databse
		con = (Connection) DriverManager.getConnection(url, user, password);
		
		// displaying all the rows from the sql table employees.
		String sql = "select *from employees";
					
		// Creating Statement
		smt2 = (PreparedStatement) con.prepareStatement(sql);

		// Set auto-commit to false
		con.setAutoCommit(false);
		
		// storing the result in the resultSet.
		rs = smt2.executeQuery(sql); // need to add throws declaration.

			// displaying the result.
			while (rs.next())
			{
				int id = rs.getInt("id");
				String lastName = rs.getString("lname");
				String firstName = rs.getString("fname");
				String email = rs.getString("email_id");
				String dept1 = rs.getString("dept");
				double salary = rs.getDouble("salary");

				System.out.printf("%d ,%s, %s, %s, %s, %.2f\n", id, lastName, firstName, email, dept1, salary);

			}
			
			System.out.println();
			rs.close();

		

	}// method emd
	//----------------------------------------//

	private static void register() throws Exception //insert values in employees using prepared Statements
	{
		//registering the driver
		Class.forName(jdbcDriver); // The specified class loader is used to load the class or interface.

		// Connection to databse
		con = (Connection) DriverManager.getConnection(url, user, password);
		
		// displaying all the rows from the sql table employees.
		String sql = "INSERT INTO employees " 
				+ "(id, lname, fname ,email_id , dept , salary) VALUES (?,?,?,?,?,?)";
		
					
		// Creating Statement
		smt1 = (PreparedStatement) con.prepareStatement(sql);

		// Set auto-commit to false
		con.setAutoCommit(false);
		
		System.out.println("For registering yourself please set your details first");
		
		// adding the values to smt
		System.out.println("enter your unique ID");
		int id = sc.nextInt();
		((PreparedStatement) smt1).setInt(1, id );
		
		System.out.println("enter your first name");
		String lname = sc.next();
		((PreparedStatement) smt1).setString(2, lname);
		
		System.out.println("enter your last name");
		String fname = sc.next();
		((PreparedStatement) smt1).setString(3, fname);
		
		System.out.println("enter your email");
		String email_id = sc.next();
		((PreparedStatement) smt1).setString(4, email_id);
		
		System.out.println("enter your dept");
		String dept = sc.next();
		((PreparedStatement) smt1).setString(5, dept);
		
		System.out.println("enter your SALARY");
		int salary = sc.nextInt();
		((PreparedStatement) smt1).setInt(6, salary);

		smt1.executeUpdate();
		
		con.commit();
		
		login(smt1);
	}

}
