package com.google.builder;

import java.util.ArrayList;
import java.util.Scanner;

public class QueryCreate implements com.google.interaf.CreateQuery {

	@Override
	public String getCreateQuery() {
		// holds primary keys
		ArrayList<String> primaryList = null;
		// holds all coloums
		ArrayList<String> columnNames = null;

		String tname = null;
		String query = null;
		String extension = "";
		Scanner sc = null;
		try {
			// get table name
			sc = new Scanner(System.in);
			System.out.print("Enter the table name  :: ");
			tname = sc.next().toUpperCase();
			System.out.println();

			// create query
			query = "CREATE TABLE " + tname + "(";
			int primaryKeyCount = 0;
			String keyCol = "";

			primaryList = new ArrayList<>();
			columnNames = new ArrayList<>();

			while (true) {

				// take coloumn details
				System.out.print("Enter Coloumn name  :: ");
				String colName = sc.next().toUpperCase();
				if (columnNames.contains(colName)) {
					System.out.println("Column already exists . plz rerun the query");
					break;
				} else {
					columnNames.add(colName);
					System.out.print("Enter column type and size \"Format type(size)\"  :: ");
					String colTypeSize = sc.next().toUpperCase();

					System.out.print("Is it primary key ? say yes or no  :: ");
					String primaryKeyOption = sc.next();
//checks for primary keys
					if (!primaryKeyOption.equalsIgnoreCase("no")) {
						if (primaryList.contains(colName)) {
							System.out.println("Column name exists");
						} else {
							primaryList.add(colName);
							keyCol = keyCol + "," + colName;
							primaryKeyCount++;
						}

					}

					query = query + colName + " " + colTypeSize;
					// loop again on user choice
					System.out.print("More coloums?  say yes or no  :: ");
					String columnOption = sc.next();
					if (!columnOption.equalsIgnoreCase("no")) {
						query = query + ",";
						continue;
					} else {
						if (primaryKeyCount == 0) {
							extension = ")";
						} else if (primaryKeyCount == 1) {
							extension = ", PRIMARY KEY (" + primaryList.get(0) + "))";

						} else {
							extension = ", PRIMARY KEY (" + primaryList.get(0);
							for (int i = 1; i < primaryList.size(); i++) {
								extension = extension + "," + primaryList.get(i);
							}
							extension = extension + "))";

						}
						query = query + extension;
						break;

					}

				}
			}

		} catch (NullPointerException npe) {
			npe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (sc != null)
					sc.close();
				primaryList.clear();
				columnNames.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return query;
	}

}
