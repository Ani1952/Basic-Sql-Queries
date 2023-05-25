package com.google.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.builder.QueryInsert;

public class JDBC_InsertRow extends QueryInsert {
	public static void insertRow(String url,String user,String password) {

		// create necessary objects for connection establishment
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		
		

		int count = 0;
		String tName = null;
		ArrayList<String> querySet = null;
		try {

			/*
			 * load and register driver Class.forName("oracle.jdbc.driver.OracleDriver");
			 */
			// Create Connection
			con = DriverManager.getConnection(url, user, password);

			// create Statement object if connection succeeds
			if (con != null)
				st = con.createStatement();
			// get table name
			sc = new Scanner(System.in);
			System.out.print("Enter Table name  :: ");
			tName = sc.next().toUpperCase();

			// extract resultset and pass to process
			if (st != null)
				rs = st.executeQuery("SELECT * FROM " + tName);

			QueryInsert queryBiulder = new QueryInsert();

			querySet = new ArrayList<>();
			boolean set = querySet.addAll(queryBiulder.returnQuerySet(rs, tName));
			// send the SQL query to database to execute the query and receive the update
			if (set) {
				for (int i = 0; i < querySet.size(); i++) {
					count = st.executeUpdate(querySet.get(i));
					
					System.out.println(count + " row added");
				}
			}

		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
