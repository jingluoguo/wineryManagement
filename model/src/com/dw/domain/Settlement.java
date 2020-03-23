package com.dw.domain;

public class Settlement {
	private String s_level;
	private String s_name;
	private float s_sale;
	private float s_achievement;
	private float s_total;
	private float s_Winconsume;
	private float s_Deconsume;
	private float s_deposit;
	private float s_balance;
	private String s_date;
	private String s_ID;
	private String s_id;
	private float s_big;
	private float s_small;
	private float s_fund;
	private float s_tax;
	private float sumsale;
	private float sumdepoit;
	private String s_as_deposit;
	private float s_cba;
	public String getS_as_deposit() {
		return s_as_deposit;
	}

	public float getSumsale() {
		return sumsale;
	}

	public void setSumsale(float sumsale) {
		this.sumsale = sumsale;
	}

	public float getSumdepoit() {
		return sumdepoit;
	}

	public void setSumdepoit(float sumdepoit) {
		this.sumdepoit = sumdepoit;
	}

	public void setS_as_deposit(String s_as_deposit) {
		this.s_as_deposit = s_as_deposit;
	}

	public float getS_big() {
		return s_big;
	}

	public void setS_big(float s_big) {
		this.s_big = s_big;
	}

	public float getS_small() {
		return s_small;
	}

	public void setS_small(float s_small) {
		this.s_small = s_small;
	}
	public Settlement() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	public float getS_fund() {
		return s_fund;
	}

	public void setS_fund(float s_fund) {
		this.s_fund = s_fund;
	}

	public float getS_cba() {
		return s_cba;
	}

	public void setS_cba(float s_cba) {
		this.s_cba = s_cba;
	}

	public Settlement(String s_level, String s_name, float s_sale, float s_achievement, float s_total,
			float s_Winconsume, float s_Deconsume, float s_deposit, float s_balance, String s_date, String s_ID,
			String s_id2, float s_big,float s_small,float s_fund,float s_tax,String s_as_deposit) {
		super();
		this.s_level = s_level;
		this.s_name = s_name;
		this.s_sale = s_sale;
		this.s_achievement = s_achievement;
		this.s_total = s_total;
		this.s_Winconsume = s_Winconsume;
		this.s_Deconsume = s_Deconsume;
		this.s_deposit = s_deposit;
		this.s_balance = s_balance;
		this.s_date = s_date;
		this.s_ID = s_ID;
		this.s_id = s_id2;
		this.s_big = s_big;
		this.s_small = s_small;
		this.s_fund = s_fund;
		this.s_tax = s_tax;
		this.s_as_deposit = s_as_deposit;
	}
	public Settlement(String s_level, String s_name, float s_sale, float s_achievement, float s_total,
			float s_Winconsume, float s_Deconsume, float s_deposit, float s_balance, String s_date, String s_ID,
			String s_id2, float s_big,float s_small,float s_fund,float s_tax,String s_as_deposit,float s_cba) {
		super();
		this.s_level = s_level;
		this.s_name = s_name;
		this.s_sale = s_sale;
		this.s_achievement = s_achievement;
		this.s_total = s_total;
		this.s_Winconsume = s_Winconsume;
		this.s_Deconsume = s_Deconsume;
		this.s_deposit = s_deposit;
		this.s_balance = s_balance;
		this.s_date = s_date;
		this.s_ID = s_ID;
		this.s_id = s_id2;
		this.s_big = s_big;
		this.s_small = s_small;
		this.s_fund = s_fund;
		this.s_tax = s_tax;
		this.s_as_deposit = s_as_deposit;
		this.s_cba = s_cba;
	}

	public Settlement(String s_level, String s_name, float s_sale, float s_achievement, float s_total,
			float s_Winconsume, float s_Deconsume, float s_deposit, float s_balance, String s_date, String s_ID,
			String s_id2, float s_big,float s_small,float s_fund,float s_tax,String s_as_deposit,float sumsale,float sumdepoid) {
		super();
		this.s_level = s_level;
		this.s_name = s_name;
		this.s_sale = s_sale;
		this.s_achievement = s_achievement;
		this.s_total = s_total;
		this.s_Winconsume = s_Winconsume;
		this.s_Deconsume = s_Deconsume;
		this.s_deposit = s_deposit;
		this.s_balance = s_balance;
		this.s_date = s_date;
		this.s_ID = s_ID;
		this.s_id = s_id2;
		this.s_big = s_big;
		this.s_small = s_small;
		this.s_fund = s_fund;
		this.s_tax = s_tax;
		this.s_as_deposit = s_as_deposit;
		this.sumsale = sumsale;
		this.sumdepoit = sumdepoid;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getS_date() {
		return s_date;
	}
	public void setS_date(String s_date) {
		this.s_date = s_date;
	}
	public String getS_ID() {
		return s_ID;
	}
	public void setS_ID(String s_ID) {
		this.s_ID = s_ID;
	}
	public float getS_Winconsume() {
		return s_Winconsume;
	}
	public void setS_Winconsume(float s_Winconsume) {
		this.s_Winconsume = s_Winconsume;
	}
	public float getS_Deconsume() {
		return s_Deconsume;
	}
	public void setS_Deconsume(float s_Deconsume) {
		this.s_Deconsume = s_Deconsume;
	}
	public String getS_level() {
		return s_level;
	}
	public void setS_level(String s_level) {
		this.s_level = s_level;
	}
	public float getS_sale() {
		return s_sale;
	}
	public void setS_sale(float s_sale) {
		this.s_sale = s_sale;
	}
	public float getS_achievement() {
		return s_achievement;
	}
	public void setS_achievement(float s_achievement) {
		this.s_achievement = s_achievement;
	}
	public float getS_total() {
		return s_total;
	}
	public void setS_total(float s_total) {
		this.s_total = s_total;
	}
	public float getS_deposit() {
		return s_deposit;
	}
	public void setS_deposit(float s_deposit) {
		this.s_deposit = s_deposit;
	}
	public float getS_balance() {
		return s_balance;
	}
	public void setS_balance(float s_balance) {
		this.s_balance = s_balance;
	}

	public float getS_tax() {
		return s_tax;
	}

	public void setS_tax(float s_tax) {
		this.s_tax = s_tax;
	}

	@Override
	public String toString() {
		return "Settlement [s_level=" + s_level + ", s_name=" + s_name + ", s_sale=" + s_sale + ", s_achievement="
				+ s_achievement + ", s_total=" + s_total + ", s_Winconsume=" + s_Winconsume + ", s_Deconsume="
				+ s_Deconsume + ", s_deposit=" + s_deposit + ", s_balance=" + s_balance + ", s_date=" + s_date
				+ ", s_ID=" + s_ID + ", s_id=" + s_id + ", s_big=" + s_big + ", s_small=" + s_small + ", s_fund="
				+ s_fund + ", s_tax=" + s_tax + ", sumsale=" + sumsale + ", sumdepoit=" + sumdepoit + ", s_as_deposit="
				+ s_as_deposit + ", s_cba=" + s_cba + "]";
	}
}
