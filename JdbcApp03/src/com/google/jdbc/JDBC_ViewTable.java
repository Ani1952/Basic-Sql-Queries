package com.google.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.google.builder.QueryView;

public class JDBC_ViewTable extends QueryView {
	public static void viewTable(String url, String user, String password) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;

		String query = null;
		int count = 0;
		String tName = null;
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

			QueryView queryBiulder = new QueryView();
			query = queryBiulder.viewTable(rs, tName);
			rs = null;
			rs = st.executeQuery(query);
			if (rs != null)
				;
			rsmd = rs.getMetaData();
			count = rsmd.getColumnCount();
			int colCounter = 1;
			while (rs.next()) {
				while (colCounter <= count) {
					System.out.print(rs.getString(colCounter));
					System.out.print("\t");
					colCounter++;
				}
				System.out.println();
				colCounter = 1;
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
