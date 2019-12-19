package com.bridgelabz.jdbc.callableStatementsWithParameters;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;

public class RESULTSETparametersCallableStatements {
	public static void main(String[] args) throws SQLException {

		Connection con = null;
		CallableStatement smt = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String password = "password";

		try {
			// get the connection to the database
			con = (Connection) DriverManager.getConnection(url, user, password);

			// prepare the stored procedure
			smt = (CallableStatement) con.prepareCall("  {  call get_employees_from_dept1(?) }");

			String dept = "engineering";

			smt.setString(1, dept);

			System.out.println("callable procedure started");
			smt.execute();
			System.out.println("callable procedure finished");

			// storing the answer to the query inside the resultSet
			rs = smt.getResultSet();

			// displaying the result set
			while (rs.next()) {
				String lastName = rs.getString("lname");
				String firstName = rs.getString("fname");
				double salary = rs.getDouble("salary");
				String department = rs.getString("dept");

				System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (smt != null) {
				smt.close();
			}

			if (con != null) {
				con.close();
			}
		}

	}

}
