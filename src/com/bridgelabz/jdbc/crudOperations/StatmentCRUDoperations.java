package com.bridgelabz.jdbc.crudOperations;

import java.sql.* ;


public class StatmentCRUDoperations 
{
	public static void main(String[] args)
	{
			
		String url ="jdbc:mysql://localhost:3306/demo" ;
		String user ="root";
		String password = "password"; 
		
		 
		try
		{
			//connection to database
			Connection con = DriverManager.getConnection(url,user,password);
			 
			//create statement
			Statement smt = con.createStatement();  
			
			
			////write sql query of read employee table
			ResultSet rs =smt.executeQuery("select * from employees") ;
						//read tablle
			
	System.out.println("id\t  lname\t\t   fname\t\t    email_id\t\t   dept\t\t   salary\t\t");
					
			
			while(rs.next())
			{ 
				//displaying data from coloumn label and col index 
				int id = rs.getInt("id");
				String lname = rs.getString("lname");
				String fname = rs.getString("fname");
				String email_id = rs.getString("email_id");
				String dept = rs.getString("dept");
				int salary = rs.getInt("salary");
				
				
				System.out.println(+id+ "\t " +lname+ "\t\t" +fname+ 
						"\t\t " +email_id+ "\t\t " +dept+ "\t\t" +salary);
				
			}     
			
		
			
			
			
				//write sql query of insert
			String insert ="insert into employees values ('8' ,'supzz','harami' "
					+ ",'harami@gmail.com','semi',55000)" ;
			smt.executeUpdate(insert);
			System.out.println("insert completed"); 
			
			
			
	/*		//write sql query to update
			String update ="update employees set email_id ='supriyakengar7@gmail.com' where id = '1' ";
			smt.executeUpdate(update);	
			System.out.println("Update completed");   */
			
			
	/*		//write sql query to delete
			String delete ="delete from employees where fname ='thale' " ;
				int rowAffected =smt.executeUpdate(delete);
				System.out.println("row affected :"+rowAffected);
				System.out.println("Delete completed");
			  */
			
			
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
