package com.dw.domain;

public class Card {
	private int id;
	private String card_ID;
	private String card_number;
	private String card_address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCard_ID() {
		return card_ID;
	}
	public void setCard_ID(String card_ID) {
		this.card_ID = card_ID;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getCard_address() {
		return card_address;
	}
	public void setCard_address(String card_address) {
		this.card_address = card_address;
	}
	public Card(String card_ID, String card_number, String card_address) {
		super();
		this.card_ID = card_ID;
		this.card_number = card_number;
		this.card_address = card_address;
	}
	public Card() {
		super();
		// TODO 自动生成的构造函数存根
	}
}
