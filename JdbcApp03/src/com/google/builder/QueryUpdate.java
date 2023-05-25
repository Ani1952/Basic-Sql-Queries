package com.google.builder;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.interaf.UpdateQuery;

public class QueryUpdate implements UpdateQuery {

	@Override
	public ArrayList<String> returnQuerySet(ResultSet rs, String tName) {
		String query = null;
		String choice = null;
		String colNameComparator = null;
		String relation = null;
		String variable = null;

		int count = 0;

		ResultSetMetaData rsmd = null;

		// HOLDS ALL THE QUERIES
		ArrayList<String> querySet = null;
		// holds the column names
		ArrayList<String> colName = null;
		Scanner sc = null;
		try {
			querySet = new ArrayList<>();
			colName = new ArrayList<>();

			sc = new Scanner(System.in);
			// get all coloumn information
			if (rs != null)
				rsmd = rs.getMetaData();
			if (rsmd != null)
				count = rsmd.getColumnCount();

			if (count != 0)
				for (int i = 1; i <= count; i++)
					colName.add(rsmd.getColumnName(i));

			System.out.println("The columns in the table " + tName + " are ");

			for (int i = 0; i < count; i++)
				System.out.print(colName.get(i) + " ");
			System.out.println("\n\n");

			do {
				query = "UPDATE " + tName + " SET ";
				
				
				System.out.println("Enter condition to update ");
				do{
//					format ----> <colNameComparator>  <relation>  <variable>
					System.out.print("Enter Coloumn name  :: ");
					colNameComparator = sc.next().toUpperCase();
					System.out.print("Enter variable to set  :: ");
					variable = sc.next();
					query = query + colNameComparator + " = " + "\'"+variable+ "\'";
					System.out.println("DO YOU WANT MORE CONDITIONS? yes/no  :: ");
					choice = sc.next();
					if (!choice.equalsIgnoreCase("no")) {
						query = query + " , ";
						continue;
					} else {
						break;
					}
				} while (true);
				
				
				query=query+" WHERE ";
				
				
				System.out.println("Enter condition to search ");
				do {
//					format ----> <colNameComparator>  <relation>  <variable>
					System.out.print("Enter Coloumn name  :: ");
					colNameComparator = sc.next().toUpperCase();
					System.out.print("Enter The relation  :: ");
					relation = sc.next();
					System.out.print("Enter variable to compare  :: ");
					variable = sc.next();
					query = query + colNameComparator + relation + variable;
					System.out.println("DO YOU WANT MORE CONDITIONS? yes/no  :: ");
					choice = sc.next();
					if (!choice.equalsIgnoreCase("no")) {
						query = query + " AND ";
						continue;
					} else {
						break;
					}
				} while (true);
				querySet.add(query);
				System.out.println("DO YOU WANT MORE DELETIONS? yes/no  :: ");
				choice = sc.next();
				if (!choice.equalsIgnoreCase("no"))
					continue;
				else
					break;
			} while (true);
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (sc != null)
					sc.close();
			} catch (NullPointerException npe) {
				npe.printStackTrace();
			}

		}
		return querySet;
	}

}
