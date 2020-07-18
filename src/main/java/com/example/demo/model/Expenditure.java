package com.example.demo.model;

import java.util.Date;

public class Expenditure {
	public Expenditure() {
		super();
	}
	public Expenditure(String date, String item, double price, double quantity) {
		super();
		this.date = date;
		this.item = item;
		this.price = price;
		this.quantity = quantity;
	}
	private String date;
	private String item;
	private double price;
	private double quantity;
	private int id;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
