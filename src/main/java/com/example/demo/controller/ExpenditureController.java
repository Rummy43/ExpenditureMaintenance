package com.example.demo.controller;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.BattingStyle;
import com.example.demo.model.Expenditure;
import com.example.demo.service.ExpenditureService;

@Controller
public class ExpenditureController {
	
	@Autowired
	ExpenditureService expenditureService;
	
	@GetMapping(value="/getAllExpenditures")
	public @ResponseBody List<Expenditure> getAllExpenditures() throws SQLException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		List<Expenditure> l  = new ArrayList<Expenditure>();
		Connection con = null;
		try {
			con = expenditureService.getConnection() ;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from expenditure");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3)+ "  " + rs.getString(4)+ "  " + rs.getString(5));
				Expenditure e = new Expenditure();
				e.setId(rs.getInt(1));
				e.setDate(dateFormat.format(rs.getDate(2)));
				e.setItem(rs.getString(3));
				e.setPrice(rs.getDouble(4));
				e.setQuantity(rs.getDouble(5));
				l.add(e);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return l;
	}
	
	@GetMapping(value = "/delete")
	public @ResponseBody String delete(@RequestParam int id) {
		System.out.println(id);
		String status = "Record deleted successfully";
		Connection con = null;
		try {
			con = expenditureService.getConnection();
			String query = "delete from expenditure where id = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, id);
			preparedStmt.execute();
			con.close();
		} catch (Exception e) {
			status=e.getMessage();
			System.out.println(e.getMessage());
		}
		return status;
	}
	
	@GetMapping(value = "/battingTypes")
	public @ResponseBody List<BattingStyle> getBattingTypes() {
		List<BattingStyle> bsList = new ArrayList<BattingStyle>();
		BattingStyle bs = new BattingStyle();
		bs.setId(1);
		bs.setType("Right Hand");
		bsList.add(bs);
		BattingStyle bs1 = new BattingStyle();
		bs1.setId(2);
		bs1.setType("Left Hand");
		bsList.add(bs1);
		BattingStyle bs2 = new BattingStyle();
		bs2.setId(2);
		bs2.setType("Both Hand");
		bsList.add(bs2);
		return bsList;
	}
	
}
