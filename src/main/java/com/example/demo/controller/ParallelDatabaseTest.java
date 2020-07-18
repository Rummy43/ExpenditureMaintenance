package com.example.demo.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Expenditure;
import com.example.demo.service.ExpenditureService;

public class ParallelDatabaseTest {

	public static void main(String[] args) throws SQLException {
		Connection con = null;
		try {
			ExpenditureService e = new ExpenditureService();
			con = e.getConnection() ;
			List<Expenditure> list = new ArrayList<Expenditure>();
			for(int i=0;i<50;i++) {
				Expenditure ex = new Expenditure("23/06/2019","curd",i,i);
				list.add(ex);
			}
			Statement stmt = con.createStatement();
			long t1, t2;
			 t1 = System.currentTimeMillis();   
			list.parallelStream().forEach(ed->{
				String sql = "insert into expenditure(date,item,price,quantity) values(sysdate()+1,'"+ed.getItem()+"',"+ed.getPrice()+","+ed.getQuantity()+")";
				//System.out.println(sql);
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			 t2 = System.currentTimeMillis();   
			 
			 System.out.println("par:"+(t2-t1));
			 
			 
			 t1 = System.currentTimeMillis();   
				list.stream().forEach(ed->{
					String sql = "insert into expenditure(date,item,price,quantity) values(sysdate()+1,'"+ed.getItem()+"',"+ed.getPrice()+","+ed.getQuantity()+")";
					//System.out.println(sql);
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				 t2 = System.currentTimeMillis();   
				 
				 System.out.println("ser:"+(t2-t1));
			 
			 
			 
			 
			/*ResultSet rs = stmt.executeQuery("select * from expenditure");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3)+ "  " + rs.getString(4)+ "  " + rs.getString(5));
			}*/
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
