package com.google.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.builder.QueryCreate;
public class JDBC_CreateTable extends QueryCreate{

	public static void createTable(String url,String user,String password) {
		// create necessary objects for connection establishment
		Connection con = null;
		Statement st = null;
		ResultSet rs=null;
		String query = null;
		// create supporting objects
		QueryCreate queryBiulder = new QueryCreate();

		try {
			/*
			 * load and register driver Class.forName("oracle.jdbc.driver.OracleDriver");
			 */
			// Create Connection
			con = DriverManager.getConnection(url, user, password);

			// create Statement object if connection succeeds
			if (con != null)
				st = con.createStatement();

			// Prepare the SQL query
			query = queryBiulder.getCreateQuery();
			System.out.println("SQL > " + query);
			// send the SQL query to database to execute the query and receive the update
			if (st != null)
				rs = st.executeQuery(query);
			if(rs!=null)
				System.out.println("TABLE CREATED SUCCESSFUL");

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
