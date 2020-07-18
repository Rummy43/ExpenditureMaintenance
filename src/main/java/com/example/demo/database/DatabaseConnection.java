package com.example.demo.database;
import java.sql.*;

public class DatabaseConnection {
	public static void main(String[] args) {

		DatabaseConnection.getConnection();
	}

	static void getConnection() {
		System.out.println("we are in get connection..");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vits", "root", "root");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expenditures", "root", "root");
			// here sonoo is database name, root is username and password
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from expenditure");
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
