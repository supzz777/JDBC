package com.brideglabz.jdbc.batchprocessing;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class BatchProcessingWithStatementObject {

	static final String jdbcDriver = "com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost:3306/demo";
	static final String user = "root";
	static final String password = "password";

	static Connection con = null;
	static Statement smt = null;
	static ResultSet rs = null;

	public static void main(String[] args) throws Throwable, SQLException {

		try {

			Class.forName(jdbcDriver); // The specified class loader is used to load the class or interface.

			// Connection to databse
			con = (Connection) DriverManager.getConnection(url, user, password);

			// Creating Statement
			smt = (Statement) con.createStatement();

			// Set auto-commit to false
			con.setAutoCommit(false);

			// selecting all the records and printing them
			printRows(smt);

			// creating sql statement
			String sql = "insert into employees values(80,'khan','arif', 'uny@gmail.com','IT',98700)";

			// adding to the batch.
			smt.addBatch(sql);

			// creating sql statement
			sql = "insert into employees values(81,'khfn','alkrif', 'uxvvfny@gmail.com','IT',88700)";

			// adding to the batch.
			smt.addBatch(sql);

			sql = "update employees set id =87 where id =10";
			smt.addBatch(sql);

			// execute the batch and stroing the result into an array
			int[] count = smt.executeBatch();

			// commit all the queries at once
			con.commit();

			// again diaplay all the rows.
			printRows(smt);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			if (con != null)
				con.close();

			if (smt != null)
				smt.close();

			if (rs != null)
				rs.close();

		}

	}// main end.

	// --------------------------//
	private static void printRows(Statement smt) throws SQLException {

		// displaying all the rows from the sql table employees.

		String sql = "select *from employees";

		// storing the result in the resultSet.
		rs = smt.executeQuery(sql); // need to add throws declaration.

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

	}

}
