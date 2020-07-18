package com.example.demo.service;

import java.sql.*;

import org.springframework.stereotype.Service;

@Service
public class ExpenditureService {
	
	Connection con=null;
	
	public Connection getConnection() {
		System.out.println("we are in get connection..");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expenditures", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
	
}
