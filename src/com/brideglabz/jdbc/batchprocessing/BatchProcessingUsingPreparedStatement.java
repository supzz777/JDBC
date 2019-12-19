package com.brideglabz.jdbc.batchprocessing;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class BatchProcessingUsingPreparedStatement {

	static final String jdbcDriver = "com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost:3306/demo";
	static final String user = "root";
	static final String password = "password";

	// creating sql statement
	static final String sql = "INSERT INTO employees " 
			+ "(id, lname, fname ,email_id , dept , salary) VALUES (?,?,?,?,?,?)";

	static Connection con = null;
	static PreparedStatement smt1 = null;
	static ResultSet rs = null;

	public static void main(String[] args) throws Throwable, SQLException {

		try {

			Class.forName(jdbcDriver); // The specified class loader is used to load the class or interface.

			// Connection to databse
			con = (Connection) DriverManager.getConnection(url, user, password);

			// Creating Statement
			smt1 = (PreparedStatement) con.prepareStatement(sql);

			// Set auto-commit to false
			con.setAutoCommit(false);

			// selecting all the records and printing them
			printRows(smt1);

			// adding the values to smt
			((PreparedStatement) smt1).setInt(1, 98);
			((PreparedStatement) smt1).setString(2, "ashish");
			((PreparedStatement) smt1).setString(3, "nakva");
			((PreparedStatement) smt1).setString(4, "ashish@gmail.com");
			((PreparedStatement) smt1).setString(5, "IT");
			((PreparedStatement) smt1).setInt(6, 87860);

			// adding to the batch
			smt1.addBatch(); //dont add arguments in addBatch() method.

			// adding the values to smt
			((PreparedStatement) smt1).setInt(1, 99);
			((PreparedStatement) smt1).setString(2, "nayan");
			((PreparedStatement) smt1).setString(3, "nakva");
			((PreparedStatement) smt1).setString(4, "nayan@gmail.com");
			((PreparedStatement) smt1).setString(5, "engineering");
			((PreparedStatement) smt1).setInt(6, 99860);

			// adding to the batch
			smt1.addBatch();

			// Create an int[] to hold returned values
			smt1.executeBatch(); //dont add arguments in executeBatch() method.

			// Explicitly commit statements to apply changes
			con.commit();

			// Again, let us select all the records and display them.
			printRows(smt1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (con != null)
				con.close();

			if (smt1 != null)
				smt1.close();

			if (rs != null)
				rs.close();

		}

	}// main end

	// ------------------------//

	private static void printRows(PreparedStatement smt2) throws SQLException {

		// displaying all the rows from the sql table employees.

		String sql = "select *from employees";

		// storing the result in the resultSet.
		rs = smt2.executeQuery(sql); // need to add throws declaration.

		// displaying the result.
		while (rs.next()) {
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

}// class end
