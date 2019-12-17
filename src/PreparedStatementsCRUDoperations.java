import java.sql.*;

public class PreparedStatementsCRUDoperations
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
			
	/*		//create statement AND WRITE QUERY
			PreparedStatement smt1 = con.prepareStatement( "select * from employees where salary > ? and dept = ? " );
			smt1.setDouble(1, 8000);
			smt1.setString(2, "engineering");
			
			//execute query
			ResultSet rs =smt1.executeQuery();
			
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
			
				System.out.println("reading completed"); */
			
			
					//--------------------------//
		
			
		//write sql query to delete
			PreparedStatement smt2= con.prepareStatement( "delete from employees where salary > ? and dept = ? " );
			smt2.setDouble(1, 8000);
			smt2.setString(2, "engineering");
			int rowAffected = smt2.executeUpdate();
			
			System.out.println("row Affected:" +rowAffected);
			
			System.out.println("Delete completed");   
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}


}
