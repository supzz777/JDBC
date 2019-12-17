import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class CallableStatementsOperations 
{
	
	public static void main(String[] args) throws Exception
	{
		Connection con  = null;
		CallableStatement smt = null;
		
		
		String url ="jdbc:mysql://localhost:3306/demo" ;
		String user ="root";
		String password = "password";
		 
		 try
		 {
			 con = (Connection) DriverManager.getConnection(url,user,password);
			 
			 String dept = "engineering";
			 int increase_amount = 10000;
			 
			 System.out.println("salaries before\n");
			 showSalaries(con,dept);
			 
			 // Prepare the stored procedure call
				smt = (CallableStatement) con.prepareCall("{call increase_salary(?, ?)}");
						

				// Set the parameters
				smt.setString(1, dept);
				smt.setDouble(2, increase_amount);

				// Call stored procedure
				System.out.println("\n\nCalling stored procedure. "
						+ " increase_salaries_for_department('" + dept + "', " + increase_amount + ")");
				smt.execute();
				System.out.println("Finished calling stored procedure");

				// Show salaries AFTER
				System.out.println("\n\nSalaries AFTER\n");
				showSalaries(con, dept);
			 
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 
		 }
		
	}
			//-----------------------------------------------------------------//
			//method show salaries.
	private static void showSalaries(Connection myConn, String dept) throws SQLException
	{
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

			try {
				// Prepare statement
				myStmt = (PreparedStatement) myConn
						.prepareStatement(" select * from employees where dept= ? ");
	
				myStmt.setString(1, dept);
				
				// Execute SQL query
				myRs = myStmt.executeQuery();
	
				// Process result set
				while (myRs.next())
				{
					String lastName = myRs.getString("lname");
					String firstName = myRs.getString("fname");
					double salary = myRs.getDouble("salary");
					String dept1 = myRs.getString("dept");
					
					System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, dept1, salary);
				}
			} 
			catch (Exception exc)
			{
				exc.printStackTrace();
			} finally 
			{
				closeAll(myStmt, myRs);
			}

	}
			//--------------------------------------------//
			//close method
	
		private static void close(Connection con, Statement smt,ResultSet rs) throws SQLException 
				
			{
				if (rs != null)
				{
					rs.close();
				}
		
				if (smt != null) 
				{
					smt.close();
				}
		
				if (con != null)
				{
					con.close();
				}
				
			}
	
			private static void closeAll(Statement myStmt, ResultSet myRs)throws SQLException 
			{
				
				close(null, myStmt, myRs);
			}
		
			
			//---------------------------------------------//
			
			
			
}
	
	
	
/*	public static void main(String[] args) throws Exception {

		Connection myConn = null;
		CallableStatement myStmt = null;

		try {
			// Get a connection to database
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/demo", "student", "student");

			String theDepartment = "Engineering";
			int theIncreaseAmount = 10000;
			
			// Show salaries BEFORE
			System.out.println("Salaries BEFORE\n");
			showSalaries(myConn, theDepartment);

			// Prepare the stored procedure call
			myStmt = myConn
					.prepareCall("{call increase_salaries_for_department(?, ?)}");

			// Set the parameters
			myStmt.setString(1, theDepartment);
			myStmt.setDouble(2, theIncreaseAmount);

			// Call stored procedure
			System.out.println("\n\nCalling stored procedure.  increase_salaries_for_department('" + theDepartment + "', " + theIncreaseAmount + ")");
			myStmt.execute();
			System.out.println("Finished calling stored procedure");

			// Show salaries AFTER
			System.out.println("\n\nSalaries AFTER\n");
			showSalaries(myConn, theDepartment);

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(myConn, myStmt, null);
		}
	}

	private static void showSalaries(Connection myConn, String theDepartment) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// Prepare statement
			myStmt = myConn
					.prepareStatement("select * from employees where department=?");

			myStmt.setString(1, theDepartment);
			
			// Execute SQL query
			myRs = myStmt.executeQuery();

			// Process result set
			while (myRs.next()) {
				String lastName = myRs.getString("last_name");
				String firstName = myRs.getString("first_name");
				double salary = myRs.getDouble("salary");
				String department = myRs.getString("department");
				
				System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(myStmt, myRs);
		}

	}

	private static void close(Connection myConn, Statement myStmt,
			ResultSet myRs) throws SQLException {
		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}

	private static void close(Statement myStmt, ResultSet myRs)
			throws SQLException {

		close(null, myStmt, myRs);
	}
	*/


