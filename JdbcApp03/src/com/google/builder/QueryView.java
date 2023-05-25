package com.google.builder;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.interaf.ViewQuery;

public class QueryView implements ViewQuery {

	@Override
	public String viewTable(ResultSet rs, String tName) {
		String query = null;
		ArrayList<String> colList = null;
		String col = null;
		String choice = null;

		int count = 0;

		ResultSetMetaData rsmd = null;

		ArrayList<String> colName = null;
		Scanner sc = null;
		try {
			colList=new ArrayList<>();
			colName = new ArrayList<>();

			sc = new Scanner(System.in);

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

			query="SELECT ";
			do {
				System.out.print("Enter Coloumn Name to show  :: ");
				col = sc.next();
				if (col.equals("*")) {
					colList.add("*");
					break;
				} else if (!colList.contains(col))
					colList.add(col);
				System.out.print("Do you want to add more columns to show?   yes/no  :: ");
				choice = sc.next();
				if (!choice.equalsIgnoreCase("no"))
					continue;
				else
					break;
			} while (true);
			if(colList.size()==1)
				query=query+colList.get(0);
			else {
				query=query+colList.get(0);
				for(int i=1;i<colList.size();i++)
					query=query+","+colList.get(i);
			}
			query=query+" FROM "+tName;
				

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
		return query;
	}

}
