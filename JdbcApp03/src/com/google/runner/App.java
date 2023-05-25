package com.google.runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.jdbc.JDBC_CreateTable;
import com.google.jdbc.JDBC_DeleteRow;
import com.google.jdbc.JDBC_InsertRow;
import com.google.jdbc.JDBC_UpdateTable;
import com.google.jdbc.JDBC_ViewTable;

class AppUtil {
	public static void create(String url,String username,String pass) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("_______________________________________");
			System.out.println("TO CREATE ENTER 1");
			System.out.println("TO GO BACK ENTER 2");
			System.out.println("TO EXIT ENTER 3");
			System.out.print("Enter  :: ");
			int choice = Integer.parseInt(br.readLine());
			if (choice == 1) {
				System.out.println("YOU CHOSE TO CREATE TABLE");
				JDBC_CreateTable.createTable(url,username,pass);
				break;
			}
			if (choice == 2)
				break;
			if (choice == 3) {
				System.out.println("_______________________________________");
			System.out.println("_______________________________________");
			System.out.println("Thanks For Using The Application  ");
			System.out.println("_______________________________________");
			System.out.println("_______________________________________");
			System.exit(0);}
		} while (true);
		br.close();

	}

	public static void update(String url,String username,String pass) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		do {
			System.out.println("_______________________________________");
			System.out.println("TO INSERT ENTER 1");
			System.out.println("TO DELETE ENTER 2");
			System.out.println("TO UPDATE ENTER 3");
			System.out.println("TO GO BACK ENTER 4");
			System.out.println("TO EXIT ENTER 5");
			System.out.print("Enter  :: ");
			int choice = Integer.parseInt(br.readLine());
			if (choice == 1) {
				System.out.println("YOU CHOSE TO INSERT DATA IN TABLE");
				
				JDBC_InsertRow.insertRow(url,username,pass);
				break;
			}
			if (choice == 2) {
				System.out.println("YOU CHOSE TO DELETE DATA FROM TABLE");
				JDBC_DeleteRow.deleteRow(url,username,pass);
				break;
			}
			if (choice == 3) {
				System.out.println("YOU CHOSE TO UPDATE DATA OF TABLE");
				JDBC_UpdateTable.updateRow(url, username, pass);
				break;
			}

			if (choice == 4)
				break;
			if (choice == 5) {
				
				System.out.println("_______________________________________");
			System.out.println("_______________________________________");
			System.out.println("Thanks For Using The Application  ");
			System.out.println("_______________________________________");
			System.out.println("_______________________________________");
			System.exit(0);}
		} while (true);
		br.close();
	}

	public static void view (String url,String username,String pass)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("TO VIEW ENTER 1");
			System.out.println("TO GO BACK ENTER 2");
			System.out.println("TO EXIT ENTER 3");
			System.out.print("Enter  :: ");
			int choice = Integer.parseInt(br.readLine());
			if (choice == 1) {
				System.out.println("YOU CHOSE TO VIEW DATA OF TABLE");
				JDBC_ViewTable.viewTable(url, username,pass);
				break;
			}

			if (choice == 2)
				break;
			if (choice == 3) {
				System.out.println("_______________________________________");
			System.out.println("_______________________________________");
			System.out.println("Thanks For Using The Application  ");
			System.out.println("_______________________________________");
			System.out.println("_______________________________________");
			System.exit(0);}
		} while (true);br.close();
	}

}

public class App {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// url,username,password of database
		String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
		String username,pass;
		System.out.print("Enter Username  :: ");
		username=br.readLine();
		System.out.print("Enter Password  :: ");
		pass=br.readLine();
		
		
		int choice;
		do {
			System.out.println("_______________________________________");
			System.out.println("Create table-----> Enter 1");
			System.out.println("Update table-----> Enter 2");
			System.out.println("View table-----> Enter 3");
			System.out.println("EXIT---->ENTER 4");
			System.out.print("Enter  :: ");
			choice = Integer.parseInt(br.readLine());
			switch (choice) {
			case 1:
				AppUtil.create(url,username,pass);
				break;
			case 2:
				AppUtil.update(url,username,pass);
				break;
			case 3:
				AppUtil.view(url,username,pass);
				break;
			case 4:
				System.out.println("_______________________________________");
				System.out.println("_______________________________________");
				System.out.println("Thanks For Using The Application  ");
				System.out.println("_______________________________________");
				System.out.println("_______________________________________");
				System.exit(0);
				break;
			}
		} while (true);

	}

}
