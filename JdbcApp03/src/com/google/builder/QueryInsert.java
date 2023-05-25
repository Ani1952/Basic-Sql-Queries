package com.google.builder;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.interaf.InsertQuery;

public class QueryInsert implements InsertQuery {

@Override
	public ArrayList<String> returnQuerySet(ResultSet rs,String tName) {
		String query = null;
		String exten = null;
		String choice = null;
		String term=null;
		
		int count=0;
		
		ResultSetMetaData rsmd = null;
	
		ArrayList<String> querySet = null;
		ArrayList<String> colName = null;
		Scanner sc = null;
		try {
			querySet=new ArrayList<>();
			colName= new ArrayList<>();
			
			sc=new Scanner(System.in);
			
			if (rs != null)
				rsmd = rs.getMetaData();
			if (rsmd != null)
				count = rsmd.getColumnCount();
			System.out.println(count);
			if (count != 0)
				for (int i = 1; i <= count; i++)
					colName.add(rsmd.getColumnName(i));
			int colCount = colName.size();
			System.out.println("The columns in the table " + tName + " are ");

			for (int i = 0; i < count; i++)
				System.out.print(colName.get(i) + " ");
			System.out.println("\n\n");
			
			
			do {
				
				query = "INSERT INTO " + tName + " VALUES";
				exten = "(";
				if (colCount == 1) {
					System.out.print("Enter value of " + colName.get(0) + "  :: ");
					term=sc.next();
					term="\'"+term+"\'";
					exten=exten+term+")";
					
				}
				else {
					
					System.out.print("Enter value of " + colName.get(0) + "  :: ");
					term=sc.next();
					term="\'"+term+"\'";
					exten=exten+term;
					for(int i=1;i<colCount;i++) {
						System.out.print("Enter value of " + colName.get(i) + "  :: ");
						term=sc.next();
						term="\'"+term+"\'";
						exten=exten+","+term;
					}
					exten=exten+")";
					
				}
				query=query+exten;
				querySet.add(query);

				System.out.print("Do you want to add more rows?   yes/no  :: ");
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
		}
		finally {
			try {
				if(sc!=null)
					sc.close();
			} catch (NullPointerException npe) {
			npe.printStackTrace();
			}
		
		}

		return querySet;
	}

}
